/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.core.aspect;

import de.jgros.eercp.core.type.ImplementsUnique;
import de.jgros.eercp.core.type.Unique;

/**
 * Aspect that adds {@link Unique} as super class (by inter-type declaration) 
 * to all classes with a {@link ImplementsUnique}.
 * 
 * @author Jörg Groß
 *
 */
public privileged aspect UniqueAspect {

     declare parents : @ImplementsUnique * extends Unique;
     

}
