/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.remote.hessian;

import javax.naming.*;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Bean;

/**
 * Helper class for retrieving CDI beans using SPI/BeanManager.
 * <p>
 * @author Jörg Groß
 */
public class BeanManagerAccessor {

    /**
     * bean manager reference.
     */
    private BeanManager cdiBeanManager;

    /**
     * Retrieves and returns the bean reference from cdi context for the
     * given type.
     * <p>
     * @param <T> the parameterized type
     * @param beanClass the type class
     */
    public <T> T getReference(Class<T> beanClass) {
        BeanManager cdiManager = getBeanManager();

        Bean<T> bean = (Bean<T>) cdiManager.resolve(cdiManager.getBeans(beanClass));
        CreationalContext<T> env = cdiManager.createCreationalContext(bean);

        if (bean != null) {
            return (T) cdiManager.getReference(bean, beanClass, env);
        }
        else {
            throw new RuntimeException("No Bean for class: " + beanClass + " found!");
        }
    }

    /**
     * Returns the bean manager.
     * 
     * @return the bean manager
     */
    public BeanManager getBeanManager() {
        if (cdiBeanManager == null) {
            try {
                InitialContext ic = new InitialContext();
                cdiBeanManager = (BeanManager) ic.lookup("java:comp/BeanManager");
            } catch (Exception anEx) {
                throw new RuntimeException(anEx);
            }
        }
        return cdiBeanManager;
    }
}
