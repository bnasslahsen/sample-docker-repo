package fr.training.samples.spring.shop.domain.account;

import javax.validation.constraints.NotNull;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Bank account owner (Entity).
 */
public class Owner extends AbstractBaseEntity {

	private static final String SPACE = " ";

	/**
	 * Owner's last name
	 */
	private String lastName;

	/**
	 * Owner's first name
	 */
	private String firstName;

	/**
	 * Owner's email
	 */
	private String email;

	/**
	 * Private constructor to enforce Builder usage
	 */
	private Owner(final Builder builder) {
		lastName = builder.lastName;
		firstName = builder.firstName;
		email = builder.email;
	}

	/**
	 * Default constructor required for JPA
	 */
	public Owner() {
		super();
	}

	/**
	 * Builder static assessor
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Concatenate owner's firstName and lastName to give a full name
	 *
	 * @return the owner's full name
	 */
	public String getfullName() {
		return firstName.concat(SPACE).concat(lastName);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37) //
				.append(getLastName()) //
				.append(getFirstName()) //
				.append(getEmail()) //
				.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final Owner other = (Owner) obj;
		return new EqualsBuilder() //
				.append(getLastName(), other.getLastName()) //
				.append(getFirstName(), other.getFirstName()) //
				.append(getEmail(), other.getEmail()) //
				.isEquals();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.fr.training.samples.spring.shop.domain.entity.AbstractBaseEntity#
	 * toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE) //
				.append(getId()) //
				.append(getLastName()) //
				.append(getFirstName()) //
				.append(getEmail()) //
				.toString();
	}

	/**
	 * Builder pattern to ensure Owner object is immutable.
	 */
	public static class Builder {
		private String lastName;

		private String firstName;

		private String email;

		/**
		 * Sets the lastName to the specified value.
		 */
		public Builder lastName(@NotNull final String lastName) {
			this.lastName = lastName;
			return this;
		}

		/**
		 * Sets the firstName to the specified value.
		 */
		public Builder firstName(@NotNull final String firstName) {
			this.firstName = firstName;
			return this;
		}

		/**
		 * Sets the email to the specified value.
		 */
		public Builder email(@NotNull final String email) {
			this.email = email;
			return this;
		}

		/**
		 * Construct the Owner
		 *
		 * @return the new Owner instance
		 */
		public Owner build() {
			Validate.notNull(lastName, "Owner last name cannot be null!");
			Validate.notNull(firstName, "Owner last name cannot be null!");
			Validate.notNull(email, "Owner email cannot be null!");

			return new Owner(this);
		}
	}

}
