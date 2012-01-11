/*
 * Copyright by Jörg Groß
 */
package de.jgros.eercp.server.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author joerg gross
 */
@Entity
public class Person implements IUnique, Serializable {
    
    /**
     * the unique id.
     */
    @Id
    String uniqueId;
    
    /**
     * the firstname
     */
    String firstname;

    /**
     * The user role of this person
     */
    @OneToOne(cascade= CascadeType.ALL) @MapsId
    User user;
    
    /**
     * the lastname.
     */
    @NotNull
    String lastname;
    
    /**
     * date of birth.
     */ 
    @Temporal(TemporalType.DATE)
    Date dateOfBirth;

    /**
     * sets the id;
     * @param id 
     */
    public void setUniqueId(String id) {
        this.uniqueId = id;
    }

    /**
     * gets the id.
     * @return  the id.
     */
    public String getUniqueId() {
        return uniqueId;
    }
    
}
