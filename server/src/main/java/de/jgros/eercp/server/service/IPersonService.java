/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.server.service;

import de.jgros.eercp.server.domain.Person;

/**
 *
 * @author Jörg Groß
 */
public interface IPersonService {
    
    public Person get(String aUniqueId);
    
    public Person update(Person aPerson);

    public void delete(Person aPerson);

    public void delete(String aUniqueId);

}
