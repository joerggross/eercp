/**
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.core.type;

import javax.persistence.Id;

/**
 * Implementation of uniqueness in combination with JPA.
 * <p>
 * Adds an {@link Id} annotation to the unique id.
 * <p>
 * 
 * @author Jörg Groß
 * 
 */
public class JPAUnique extends AbstractUnique {

	/**
	 * the uniqueId.
	 */
	@Id
	protected String uniqueId;

	/**
	 * This method is marked as final and should not be overridden because the
	 * overridden implementation would not be visible by this class when this
	 * class is added as a mixin to existing classes using AOP.
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public final String getUniqueId() {
		return this.uniqueId;
	}

	/**
	 * 
	 * @{inheritDoc
	 */
	public void setUniqueId(String aUniqueId) {
		this.uniqueId = aUniqueId;
	}

}
