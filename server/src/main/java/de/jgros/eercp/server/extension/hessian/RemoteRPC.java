package de.jgros.eercp.server.extension.hessian;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class as remote accessible per Hessian protocoll.
 * 
 * @author ext_gross
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RemoteRPC {

    /**
     * the interface type to be provided remote.
     * 
     * @return see description
     */
   Class type();

   /**
    * The url to use for hession remote access.
    * 
    * @return see description
    */
   String url();
            
}
