/*
 * Copyright by Jörg Groß
 */
package de.jgros.eercp.server.domain;

import de.jgros.eercp.server.extension.hessian.RemoteRPC;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@RemoteRPC
public class Person implements IUnique, Serializable {
    
    /**
     * the unique id.
     */
    @Id
    @Column(name="uniqueId")
    String uniqueId;
    
    /**
     * the firstname
     */
    String firstname;
    
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
}
