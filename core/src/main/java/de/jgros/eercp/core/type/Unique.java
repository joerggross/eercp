/**
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.core.type;

/**
 * Implementation of simple uniqueness.
 * 
 * @author Jörg Groß
 * 
 */
public class Unique extends AbstractUnique {

	/**
	 * the uniqueId.
	 */
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
