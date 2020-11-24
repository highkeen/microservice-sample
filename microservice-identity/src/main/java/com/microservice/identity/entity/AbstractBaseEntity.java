package com.microservice.identity.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class AbstractBaseEntity.
 *
 * @param <PK>
 *            the generic type
 */
@MappedSuperclass

/**
 * Gets the row version nbr.
 *
 * @return the row version nbr
 */

/**
 * Gets the row version nbr.
 *
 * @return the row version nbr
 */
@Getter

/**
 * Sets the row version nbr.
 *
 * @param rowVersionNbr the new row version nbr
 */

/**
 * Sets the row version nbr.
 *
 * @param rowVersionNbr the new row version nbr
 */
@Setter
public abstract class AbstractBaseEntity<PK> implements BaseEntity<PK> {

	

	/**  The STATUS. */
	@Column(name = "active", nullable = false)
	private Boolean active=Boolean.TRUE;

	/** The ROW_VERSION_NBR. */
	@Version
	@Column(name = "row_version_nbr", nullable = false)
	private long rowVersionNbr;

	

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getKey() == null) ? 0 : getKey().hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!getClass().isAssignableFrom(obj.getClass()))
			return false;
		AbstractBaseEntity<?> other = (AbstractBaseEntity<?>) obj;
		if (getKey() == null) {
			if (other.getKey() != null)
				return false;
		} else if (!getKey().equals(other.getKey()))
			return false;
		return true;
	}
}
