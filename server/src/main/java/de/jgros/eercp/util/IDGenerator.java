/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.util;

import java.util.UUID;

/**
 *
 * @author Jörg Groß
 */
public class IDGenerator {
    
    public String generateUUIDFor(Class aType) {
        return UUID.randomUUID().toString();
    }
    
}
