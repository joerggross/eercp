/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.server.service;

import de.jgros.eercp.server.domain.Person;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jörg Groß
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless
public class PersonService implements IPersonService {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void delete(Person aPerson) {
        em.remove(aPerson);
    }

    @Override
    public void delete(String aUniqueId) {
        em.remove(em.find(Person.class, aUniqueId));
    }

    @Override
    public Person get(String aUniqueId) {
        return em.find(Person.class, aUniqueId);
    }

    @Override
    public Person update(Person aPerson) {
        return em.merge(aPerson);
    }
    
}
