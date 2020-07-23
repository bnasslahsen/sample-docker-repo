package fr.training.samples.spring.shop.domain.common.vo;

import java.math.BigDecimal;
import java.util.Currency;

import fr.training.samples.spring.shop.domain.common.exception.NotSupportedException;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.springframework.lang.NonNull;

/**
 * Monetary amount Value Object.
 */
public final class Money {

  public static final Currency EUR = Currency.getInstance("EUR");

  public static final Currency DefaultCurrency = EUR;

	@NonNull
	private BigDecimal amount;

	@NonNull
	private Currency currency;

	/**
	 * Default constructor required for JPA
	 */
	protected Money() {
		super();
	}

	/**
	 * All args Constructor
	 *
	 * @param amount
	 *            the monetary amount
	 * @param currency
	 *            the currency
	 */
	public Money(final BigDecimal amount, final Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}

	/**
	 * Get amount
	 *
	 * @return the monetary amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Get currency
	 *
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * Negate monetary amount
	 *
	 * @return New negate Monetary Amount.
	 */
	public Money negated() {
		return of(amount.negate(), currency);
	}

	/**
	 * Add monetary amount to another monetary amount.
	 *
	 * @param other
	 *            the monetary amount to add
	 * @return New monetary amount sum.
	 * @throws NotSupportedException
	 *             if amounts hasn't same currencies
	 */
	public Money add(final Money other) {
		Validate.notNull(other);
		if (!currency.equals(other.currency)) {
			throw new NotSupportedException(
					String.format("Currency %s not match account currency %s and conversion is not Supported yet",
							currency, other.currency));
		}
		return of(amount.add(other.amount), currency);
	}

	/**
	 * Substract monetary amount from another monetary amount.
	 *
	 * @param other
	 *            the monetary amount to substract
	 * @return New Monetary Amount substraction.
	 */
	public Money substract(final Money other) {
		return add(other.negated());
	}

	public boolean isGreaterThan(final Money other) {
		Validate.isTrue(currency.equals(other.currency));
		return isGreaterThan(other.amount);
	}

	private boolean isGreaterThan(final BigDecimal amount) {
		return this.amount.compareTo(amount) > 0;
	}

	public boolean isLessThan(final Money other) {
		Validate.isTrue(currency.equals(other.currency));
		return isLessThan(other.amount);
	}

	private boolean isLessThan(final BigDecimal amount) {
		return this.amount.compareTo(amount) < 0;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37) //
				.append(getAmount()) //
				.append(getCurrency().getCurrencyCode()) //
				.toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof Money)) {
			return false;
		}
		final Money other = (Money) obj;
		return amount.compareTo(other.amount) == 0
				&& currency.getCurrencyCode().equals(other.currency.getCurrencyCode());
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)//
				.append("amount", amount) //
				.append("currency", currency) //
				.toString();
	}

	// Static methods
	/**
	 * Factory method for Constructing an IMMUTABLE money object from a int.
	 *
	 * @param amount
	 *            int amount value
	 * @return a new Money instance
	 */
	public static Money of(final int amount) {
		return of(BigDecimal.valueOf(amount));
	}

	/**
	 * Factory method for Constructing an IMMUTABLE money object from a long.
	 *
	 * @param amount
	 *            long amount value
	 * @return a new Money instance
	 */
	public static Money of(final long amount) {
		return of(BigDecimal.valueOf(amount));
	}

	/**
   * Factory method for Constructing an IMMUTABLE money object from a BigDecimal.
   *
   * @param amount BigDecimal mount value
   * @param currency currency value
   * @return a new Money instance
   */
	public static Money of(final BigDecimal amount, final Currency currency) {
		return new Money(amount, currency);
	}

	/**
	 * Factory method for Constructing an IMMUTABLE money object from a BigDecimal
	 * and default currency.
	 *
	 * @param amount
	 *            BigDecimal mount value
	 * @return a new Money instance
	 */
	public static Money of(final BigDecimal amount) {
		return of(amount, DefaultCurrency);
	}

	public static Money zero(final Currency currency) {
		return of(BigDecimal.ZERO, currency);
	}

	public static Money zero() {
		return zero(DefaultCurrency);
	}

	public boolean isZero() {
		return amount.signum() == 0;
	}

}