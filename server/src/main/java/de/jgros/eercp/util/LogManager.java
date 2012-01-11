/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.util;

import java.util.logging.Logger;

/**
 *
 * @author Jörg Groß
 */
public class LogManager {
    
    public Logger getLogger(Object aType) {
        
        return Logger.getLogger(aType.getClass().getName());
    }
    
}
