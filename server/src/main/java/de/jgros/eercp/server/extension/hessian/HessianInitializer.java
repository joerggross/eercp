/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.server.extension.hessian;

import de.jgros.eercp.server.extension.BeanManagerAccessor;
import java.lang.annotation.Annotation;
import java.util.Collection;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Jörg Groß
 */
public class HessianInitializer implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized");

        BeanManagerAccessor accessor = new BeanManagerAccessor();

        RemoteCallableDiscoveryExtension hessianExtension = accessor.getReference(RemoteCallableDiscoveryExtension.class);
        Collection<AnnotatedType> annotatedTypes = hessianExtension.getRemoteCallableAnnotatedTypes();

        for (AnnotatedType annoType : annotatedTypes) {

            RemoteCallable anno = annoType.getAnnotation(RemoteCallable.class);
            Class iface = anno.type();
            String url = anno.url();
            Class implClass = annoType.getJavaClass();

            boolean stateless = annoType.getAnnotation(Stateless.class) == null ? false : true;
            boolean statefull = annoType.getAnnotation(Stateful.class) == null ? false : true;
            System.out.println("**  Interface:" + iface);
            System.out.println("**  Url:      " + url);

            Object instance = null;
            if (stateless || statefull) {
                try {
                    InitialContext ctx = new InitialContext();
                    System.out.println(sce.getServletContext().getContextPath());
                    instance = ctx.lookup("java:app/eercp.server/ejb/PersonService");
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

            // TODO: if this fails try to resolve instance from jndi?

            System.out.println(instance);
            //        sce.getServletContext().addServlet(iface.getName(), null);

        }



    }
}
