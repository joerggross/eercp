/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.remote.hessian;

import com.caucho.hessian.server.HessianServlet;
import java.util.Collection;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;

/**
 * Servlet context listener that registers all {@link RemoteCallable} annotated
 * beans as hessian servlets by using a generic dynamic proxy apporach.
 * <p>
 * @author Jörg Groß
 */
public class RemoteHessianInitializer implements ServletContextListener {

    /**
     * {@inheritDoc }
     */ 
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // left intentionally blank
    }

    /**
     * Context listener callback.
     * <p>
     * Registers each @RemoteCallable-annotated bean as servlet.
     * <p>
     * @param sce the servlet context
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        BeanManagerAccessor accessor = new BeanManagerAccessor();

        RemoteCallableDiscoveryExtension hessianExtension = accessor.getReference(RemoteCallableDiscoveryExtension.class);
        Collection<AnnotatedType> annotatedTypes = hessianExtension.getRemoteCallableAnnotatedTypes();

        for (AnnotatedType annoType : annotatedTypes) {

            RemoteCallable anno = annoType.getAnnotation(RemoteCallable.class);
            Class iface = anno.type();
            String url = anno.url();
            Class implClass = annoType.getJavaClass();

            // bean instance to be made remote accessible
            Object instance = null;

            // check if its an ejb
            Stateless stateless = annoType.getAnnotation(Stateless.class);
            Stateful statefull = annoType.getAnnotation(Stateful.class);

            // try to retrieve ejb from jndi
            if (stateless != null || statefull != null) {
                try {
                    String ejbName = null;
                    if (stateless != null) {
                        ejbName = stateless.name();
                    } else if (statefull != null) {
                        ejbName = statefull.name();
                    }
                    if (ejbName != null) {

                        InitialContext ctx = new InitialContext();
                        String webAppName = sce.getServletContext().getContextPath();
                        // TODO the jndi-ejb root path (path building) should be configurable
                        instance = ctx.lookup("java:app" + webAppName + "/" + ejbName);
                    } else {
                        throw new RuntimeException("The EJB " + ejbName + " using @RemoteCallable must define an EJB JNDI name");
                    }

                } catch (Exception ex) {
                    // TODO log error
                    System.out.println(ex);
                }
            } else {
                try {
                    instance = accessor.getReference(implClass);
                } catch (Exception ex) {
                    // TODO log error
                    System.out.println(ex);
                }
            }
            if (instance != null) {

                // TODO log
                System.out.println("Register Hessian servlet proxy for: ");
                System.out.println("  **  Interface: " + iface);
                System.out.println("  **  Url:       " + url);

                // create hession servlet proxy
                Object proxyImpl = ExtendedHessianServletProxy.createProxy(iface, instance);

                // register as servlet
                ServletRegistration.Dynamic dynamic = sce.getServletContext().addServlet(iface.getName(), (HessianServlet) proxyImpl);
                // register url mapping
                dynamic.addMapping(url);
            }
        }
    }
}
