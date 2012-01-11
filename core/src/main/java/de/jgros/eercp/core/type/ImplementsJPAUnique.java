/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.core.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.jgros.eercp.core.aspect.JPAUniqueAspect;

/**
 * Annotation that signals the implementation of the {@link IUnique} interface
 * by the {@link JPAUniqueAspect}.
 * <p>
 * 
 * @author Jörg Groß
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ImplementsJPAUnique {

}
