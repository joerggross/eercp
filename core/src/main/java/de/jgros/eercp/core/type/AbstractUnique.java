/**
 * Copyright by Jörg Groß.
 */
package de.jgros.eercp.core.type;

/**
 * Abstract base class for implementations of {@link IUnique}.
 * 
 * @author Jörg Groß
 * 
 */
public abstract class AbstractUnique implements IUnique {

	/**
	 * 
	 * {@inheritDoc}
	 */
	public abstract String getUniqueId();

	/**
	 * @{inheritDoc
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.getUniqueId() == null) ? 0 : this.getUniqueId()
						.hashCode());
		return result;
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (IUnique.class.isInstance(obj.getClass())) {
			return false;
		}
		IUnique other = (IUnique) obj;
		if (this.getUniqueId() == null) {
			if (other.getUniqueId() != null) {
				return false;
			}
		} else if (!this.getUniqueId().equals(other.getUniqueId())) {
			return false;
		}
		return true;
	}

}
