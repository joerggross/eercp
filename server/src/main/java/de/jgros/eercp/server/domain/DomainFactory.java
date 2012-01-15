/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.server.domain;

import de.jgros.eercp.util.IDGenerator;
import javax.inject.Inject;

/**
 *
 * @author Jörg Groß
 */
public class DomainFactory {

    @Inject
    private IDGenerator idGenerator;

    public <T> T createDomainInstance(Class<T> aType) {
        try {
            Object domainObject = aType.newInstance();

            if (domainObject instanceof IUnique) {
                ((IUnique) domainObject).setUniqueId(idGenerator.generateUUIDFor(aType));
            }
            return (T) domainObject;

        } catch (InstantiationException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void setIdGenerator(IDGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
    
    
}
