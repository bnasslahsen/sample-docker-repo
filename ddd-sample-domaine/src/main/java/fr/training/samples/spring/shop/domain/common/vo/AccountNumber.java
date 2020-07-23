package fr.training.samples.spring.shop.domain.common.vo;

import java.io.Serializable;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * AccountNumber value object.
 */
public final class AccountNumber implements Serializable {

	private static final long serialVersionUID = 6888997624020049876L;

	private String value;

	/**
	 * Default constructor required for JPA
	 */
	protected AccountNumber() {
		super();
	}

	/**
	 * private construtor used to centralize validation.
	 * 
	 * @param value
	 *            value of the AccountNumber
	 */
	private AccountNumber(final String value) {
		Validate.matchesPattern(value, "^[a-zA-Z]{2}[0-9]{2}[30004]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}$",
				"IBAN invalid Format");
		this.value = value;
	}

	/**
	 * Factoy method used to create instance of AccountNumber in static way.
	 * 
	 * @param value
	 *            value of the AccountNumber
	 * @return a new AccountNumber object
	 */
	public static AccountNumber of(final String value) {
		return new AccountNumber(value);
	}

	/**
	 * @return the AccountNumber value
	 */
	public String getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37) //
				.append(value) //
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
		final AccountNumber other = (AccountNumber) obj;
		return new EqualsBuilder() //
				.append(value, other.value) //
				.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE) //
				.append(value) //
				.toString();
	}

}
