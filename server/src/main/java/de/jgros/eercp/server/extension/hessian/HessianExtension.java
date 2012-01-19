package de.jgros.eercp.server.extension.hessian;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

/**
 *
 * @author ext_gross
 */
public class HessianExtension implements Extension {

    private Collection<AnnotatedType> annotatedTypes = new ArrayList<AnnotatedType>();

    void beforeBeanDiscovery(@Observes BeforeBeanDiscovery bbd) {
        //   System.out.println("beforeBeanDiscovery: " + bbd.toString());
    }

     <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> pat) {

        AnnotatedType<T> type = pat.getAnnotatedType();

        Annotation anno = type.getAnnotation(RemoteRPC.class);

        if (anno != null) {
            System.out.println(anno);
            annotatedTypes.add(type);
        }

    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd) {
        //     System.out.println("afterBeanDiscovery: " + abd.toString());
    }

    @Override
    public String toString() {
        return super.toString() + ":" + this.annotatedTypes.toString();
    }

    /**
     * Returns the annotated types.
     */
    public Collection<AnnotatedType> getAnnotatedTypes() {
        return annotatedTypes;
    }
    
    
}
