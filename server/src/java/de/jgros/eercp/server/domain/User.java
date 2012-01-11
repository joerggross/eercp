/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jgros.eercp.server.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author joerg gross
 */
@Entity
public class User {
    
    @Id
    Long id;
    
    String firstname;
    
    @NotNull
    String lastname;
    
    @NotNull
    String password;
    
    @Temporal(TemporalType.DATE)
    Date dateOfBirth;
    
    
}
