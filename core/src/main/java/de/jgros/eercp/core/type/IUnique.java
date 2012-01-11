/*
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.core.type;

/**
 * Implementors of this interface must support a domain wide uniqueness.
 * "domain wide" means that each domain instance must have its own unique id not
 * only for instances of its own class but also for all instances in the domain.
 * 
 * 
 * @author Jörg Groß
 */
public interface IUnique {

	/**
	 * Returns the unique id.
	 */
	String getUniqueId();

	/**
	 * Sets the unique id.
	 * 
	 * @param aUniqueId
	 *            the unique id to set.
	 */
	void setUniqueId(String aUniqueId);

	/**
	 * This method that is defined here {@link Object#hashCode()} is just part
	 * of this interface to clarify that this method must be implemented when
	 * this interface is implemented.
	 */
	int hashCode();

	/**
	 * This method that is defined here {@link Object#equals(Object)} is just
	 * part of this interface to clarify that this method must be implemented
	 * when this interface is implemented.
	 */
	boolean equals(Object obj);

}
