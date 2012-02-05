/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.server.extension.hessian;

import com.caucho.hessian.server.HessianServlet;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

/**
 * This class extends a hessian servlet and holds a business delegate.
 * <p>
 * By a dynamic proxy mechanism this class is extended so that every call
 * to an interface is gelegated to the delegate.
 *  <p>
 * @author Jörg Groß
 */
public class ExtendedHessianServletProxy extends HessianServlet {

    /**
     * the delegate.
     */
    Object delegate;

    /**
     * Returns the delegate.
     * @return the delegate
     */
    public Object getDelegate() {
        return delegate;
    }

    /**
     * Sets the delegate.
     * 
     * @param aDelegate the delegate to set
     */
    public void setDelegate(Object aDelegate) {
        this.delegate = aDelegate;
    }

    /**
     * The returned proxy inherits a hession servlet that can dynamically 
     * used as a hession business proxy for the given interface.
     * <p>
     * Creates a proxy that implements the given interface and uses the given
     * implementor to delegate any call of the given interface to.
     * <p>
     * The given implementor must implement the given interface.
     * <p>
     * 
     * @param anInterfaceToImplement the business interface to be implemented by
     * the returned proxy
     * @param anImplementor the implementor (must implement the given interface)
     * @return see description.
     */
    public static Object createProxy(final Class anInterfaceToImplement, final Object anImplementor) {

        ProxyFactory f = new ProxyFactory();
        f.setSuperclass(ExtendedHessianServletProxy.class);
        f.setInterfaces(new Class[]{anInterfaceToImplement});
        f.setFilter(new MethodFilter() {

            public boolean isHandled(Method aMethod) {

                // only acept all methods of the given interface
                for (Method method : anInterfaceToImplement.getDeclaredMethods()) {
                    if (method.equals(aMethod)) {
                        return true;
                    }
                }
                return false;
            }
        });
        Class c = f.createClass();
        MethodHandler mi = new MethodHandler() {

            public Object invoke(Object self, Method aMethodCalled, Method proceed,
                    Object[] args) throws Throwable {

                ExtendedHessianServletProxy proxy = (ExtendedHessianServletProxy) self;
                return aMethodCalled.invoke(proxy.getDelegate(), args);  // execute the delegate method.
            }
        };
        ExtendedHessianServletProxy proxy;
        try {
            proxy = (ExtendedHessianServletProxy) c.newInstance();
            ((ProxyObject) proxy).setHandler(mi);
        } catch (InstantiationException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }

        proxy.setDelegate(anImplementor);
        return proxy;
    }
}
