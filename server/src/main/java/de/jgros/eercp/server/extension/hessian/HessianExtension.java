package de.jgros.eercp.server.extension.hessian;


import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;



/**
 *
 * @author ext_gross
 */


public class HessianExtension  implements Extension {
    
   void beforeBeanDiscovery(@Observes BeforeBeanDiscovery bbd) {

      System.out.println("beforeBeanDiscovery: " + bbd.toString());
              

   }

      

   <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> pat) {

      System.out.println("processAnnotatedType: " + pat.toString());

   } 


   void afterBeanDiscovery(@Observes AfterBeanDiscovery abd) {

      System.out.println("afterBeanDiscovery: " + abd.toString());

   }

   


}
