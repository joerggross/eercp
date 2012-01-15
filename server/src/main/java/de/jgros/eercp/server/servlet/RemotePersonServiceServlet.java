/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.server.servlet;

import com.caucho.hessian.server.HessianServlet;
import de.jgros.eercp.server.domain.Person;
import de.jgros.eercp.server.service.IPersonService;
import de.jgros.eercp.server.service.PersonService;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author Jörg Groß
 */

public class RemotePersonServiceServlet  extends HessianServlet implements IPersonService {
    
    @EJB
    IPersonService personService;

    @Override
    public void delete(Person aPerson) {
        personService.delete(aPerson);
    }

    @Override
    public void delete(String aUniqueId) {
        personService.delete(aUniqueId);
    }

    @Override
    public Person get(String aUniqueId) {
        return personService.get(aUniqueId);
    }

    @Override
    public Person update(Person aPerson) {
        return personService.update(aPerson);
    }
 }
