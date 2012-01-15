/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.server.servlet;

import com.caucho.hessian.client.HessianProxyFactory;
import de.jgros.eercp.server.domain.DomainFactory;
import de.jgros.eercp.server.domain.Person;
import de.jgros.eercp.server.service.IPersonService;
import de.jgros.eercp.util.IDGenerator;
import java.net.MalformedURLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jörg Groß
 */
public class RemotePersonServiceTest {
    
    private IPersonService remotePersonService;
    
    private DomainFactory domainFactory;
    
        
    public RemotePersonServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() throws MalformedURLException {
        
        String url = "http://localhost:8080/eercp.server/personService";
        HessianProxyFactory factory = new HessianProxyFactory();
        this.remotePersonService = (IPersonService) factory.create(IPersonService.class,url);
        assertNotNull(remotePersonService);
        
        this.domainFactory = new DomainFactory();
        domainFactory.setIdGenerator(new IDGenerator());
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of delete method, of class RemotePersonService.
     */
    @Test
    public void testDelete_Person() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class RemotePersonService.
     */
    @Test
    public void testDelete_String() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class RemotePersonService.
     */
    @Test
    public void testGet() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class RemotePersonService.
     */
    @Test
    public void testUpdate() {
        
        Person person = this.domainFactory.createDomainInstance(Person.class);
        
        person.setFirstname("Jörg");
        person.setLastname("Groß");
        
        person = this.remotePersonService.update(person);
        
        
        
        
        
    }
}
