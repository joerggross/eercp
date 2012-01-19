/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.server.extension;

import javax.naming.*;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Bean;
import java.util.*;

/**
 *
 * @author Jörg Groß
 */
public class BeanManagerAccessor {

    private  BeanManager cdiBeanManager;

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
