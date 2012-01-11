/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.server.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jörg Groß
 */
@Entity
public class User {
    
    @Id
    String id;
    
    @NotNull
    String username;
    
    @NotNull
    String password;
    
}
