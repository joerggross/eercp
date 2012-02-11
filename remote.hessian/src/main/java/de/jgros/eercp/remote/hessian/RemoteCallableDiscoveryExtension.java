/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.remote.hessian;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

/**
 * Observes annotated types and discovers bean that are annotated
 * with {@link RemoteCallable}. Such beans are added to the local state and
 * could be retrieved by 
 * {@link RemoteCallableDiscoveryExtension#getRemoteCallableAnnotatedTypes() }.
 * <p>
 * @author ext_gross
 */
public class RemoteCallableDiscoveryExtension implements Extension {

    /**
     * the list of remote callable annotated types.
     */
    private Collection<AnnotatedType> annotatedTypes = new ArrayList<AnnotatedType>();

    /**
     * {@inheritDoc }
     * <p>
     * Retrieves all {@link RemoteCallable} beans and adds them to the internal
     * state. After CDI processing is done all such beans are provided
     * by {@link RemoteCallableDiscoveryExtension#getRemoteCallableAnnotatedTypes() }.
     */
     <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> pat) {

        AnnotatedType<T> type = pat.getAnnotatedType();

        Annotation anno = type.getAnnotation(RemoteCallable.class);

        if (anno != null) {
            annotatedTypes.add(type);
            System.out.println(type);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String toString() {
        return super.toString() + ":" + this.annotatedTypes.toString();
    }

    /**
     * Returns the annotated types.
     * @return see description.
     */
    public Collection<AnnotatedType> getRemoteCallableAnnotatedTypes() {
        return annotatedTypes;
    }
}
