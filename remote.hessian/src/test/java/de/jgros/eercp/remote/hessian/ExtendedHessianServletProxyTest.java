/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.remote.hessian;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the class {@link ExtendedHessianServletProxy}.
 * 
 * @author Jörg Groß
 */
public class ExtendedHessianServletProxyTest {
    
    /**
     * constructor.
     */
    public ExtendedHessianServletProxyTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * test interface.
     */
    static interface ITestBusiness {
        void setFoo(String aFoo);
        String getFoo();
    }
    
    /**
     * test implementor
     */
    static class TestBusinessImpl implements ITestBusiness {
        
        String foo;

        @Override
        public String getFoo() {
            return foo;
        }

        @Override
        public void setFoo(String aFoo) {
            this.foo = aFoo;
        }
        
    }

    /**
     * Test of createProxy method, of class ExtendedHessianServletProxy.
     */
    @Test
    public void testCreateProxy() {
        
        Class anInterfaceToImplement = ITestBusiness.class;
        TestBusinessImpl anImplementor = new TestBusinessImpl();
        ITestBusiness testImpl = (ITestBusiness) ExtendedHessianServletProxy.createProxy(anInterfaceToImplement, anImplementor);
        
        testImpl.setFoo("Hello");
        Assert.assertEquals("Hello", anImplementor.getFoo());
    }
}
