/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.server.extension.hessian;

import de.jgros.eercp.server.extension.BeanManagerAccessor;
import java.lang.annotation.Annotation;
import java.util.Collection;
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

        HessianExtension hessianExtension = accessor.getReference(HessianExtension.class);
        Collection<AnnotatedType> annotatedTypes = hessianExtension.getAnnotatedTypes();

        for (AnnotatedType annoType : annotatedTypes) {

            RemoteRPC anno = annoType.getAnnotation(RemoteRPC.class);
            Class iface = anno.type();
            String url = anno.url();
            Class implClass = annoType.getJavaClass();

            //      Object instance = accessor.getReference(implClass);

            // TODO: if this fails try to resolve instance from jndi?

            System.out.println("**  Interface:" + iface);
            System.out.println("**  Url:      " + url);
            //    System.out.println("**  Instance: " + instance);

            //  javax.ejb.EJB
        }

        try {
            InitialContext ctx = new InitialContext();
            Object obj = ctx.lookup("java:app/eercp.server/ejb/PersonService");
            System.out.println(obj);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
