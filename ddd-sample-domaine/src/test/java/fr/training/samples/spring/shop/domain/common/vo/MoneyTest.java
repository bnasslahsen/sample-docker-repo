package fr.training.samples.spring.shop.domain.common.vo;


import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Tests for the class <code>{@link Money}</code>.
 */
public class MoneyTest {

	/**
	 * Money(BigDecimal,Currency) constructor test.
	 */
	@Test
	public void testMoney_1() {
		final BigDecimal amount = new BigDecimal(1.0);
		final Currency currency = Currency.getInstance(Locale.getDefault());
		// When
		final Money result = new Money(amount, currency);

		// Then
		assertThat(result, notNullValue());
		assertThat(result.isZero(), equalTo(false));
	}

	/**
	 * Money add(Money) method test.
	 */
	@Test
	public void testAdd_1() {
		final Money fixture = Money.of(new BigDecimal(1.0), Currency.getInstance(Locale.getDefault()));
		final Money other = Money.of(new BigDecimal(1.0), Currency.getInstance(Locale.getDefault()));
		// When
		final Money result = fixture.add(other);

		// Then
		assertThat(result, notNullValue());
		assertThat(result.isZero(), equalTo(false));
	}

	/**
	 * BigDecimal getAmount() method test.
	 */
	@Test
	public void testGetAmount_1() {
		final Money fixture = Money.of(new BigDecimal(1.0), Currency.getInstance(Locale.getDefault()));
		// When
		final BigDecimal result = fixture.getAmount();

		// Then
		assertThat(result, notNullValue());
		assertThat(result.toString(), equalTo("1"));
		assertThat(result.intValue(), equalTo(1));
		assertThat(result.longValue(), equalTo(1L));
		assertThat(result.signum(), equalTo(1));
		assertThat(result.scale(), equalTo(0));
		assertThat(result.byteValueExact(), equalTo((byte) 1));
		assertThat(result.intValueExact(), equalTo(1));
		assertThat(result.longValueExact(), equalTo(1L));
		assertThat(result.shortValueExact(), equalTo((short) 1));
		assertThat(result.toEngineeringString(), equalTo("1"));
		assertThat(result.toPlainString(), equalTo("1"));
		assertThat(result.precision(), equalTo(1));
		assertThat(result.byteValue(), equalTo((byte) 1));
		assertThat(result.shortValue(), equalTo((short) 1));
	}

	/**
	 * Currency getCurrency() method test.
	 */
	@Test
	public void testGetCurrency_1() {

    // final Money fixture = Money.of(new BigDecimal(1.0), Currency.getInstance(Locale.getDefault()));
    // // When
    // final Currency result = fixture.getCurrency();
    //
    // // Then
    // assertThat(result, notNullValue());
    // assertThat(result.toString(), equalTo("EUR"));
    // assertThat(result.getDisplayName(), equalTo("euro"));
    // assertThat(result.getDefaultFractionDigits(), equalTo(2));
    // assertThat(result.getCurrencyCode(), equalTo("EUR"));
    // assertThat(result.getSymbol(), equalTo("€"));
    // assertThat(result.getNumericCode(), equalTo(978));
	}

	/**
	 * boolean isGreaterThan(Money) method test.
	 */
	@Test
	public void testIsGreaterThan_1() {
		final Money fixture = Money.of(new BigDecimal(1.0), Currency.getInstance(Locale.getDefault()));
		final Money other = Money.of(new BigDecimal(1.0), Currency.getInstance(Locale.getDefault()));
		// When
		final boolean result = fixture.isGreaterThan(other);

		// Then
		assertThat(result, equalTo(false));
	}

	/**
	 * boolean isGreaterThan(Money) method test.
	 */
	@Test
	public void testIsGreaterThan_2() {
		final Money fixture = Money.of(new BigDecimal(1.0), Currency.getInstance(Locale.getDefault()));
		final Money other = Money.of(new BigDecimal(2.0), Currency.getInstance(Locale.getDefault()));
		// When
		final boolean result = fixture.isGreaterThan(other);

		// Then
		assertThat(result, equalTo(false));
	}

	/**
	 * boolean isLessThan(Money) method test.
	 */
	@Test
	public void testIsLessThan_1() {
		final Money fixture = Money.of(new BigDecimal(1.0), Currency.getInstance(Locale.getDefault()));
		final Money other = Money.of(new BigDecimal(1.0), Currency.getInstance(Locale.getDefault()));
		// When
		final boolean result = fixture.isLessThan(other);

		// Then
		assertThat(result, equalTo(false));
	}

	/**
	 * boolean isLessThan(Money) method test.
	 */
	@Test
	public void testIsLessThan_2() {
		final Money fixture = Money.of(new BigDecimal(2.0), Currency.getInstance(Locale.getDefault()));
		final Money other = Money.of(new BigDecimal(1.0), Currency.getInstance(Locale.getDefault()));
		// When
		final boolean result = fixture.isLessThan(other);

		// Then
		assertThat(result, equalTo(false));
	}

	/**
	 * boolean isZero() method test.
	 */
	@Test
	public void testIsZero_1() {
		final Money fixture = Money.of(new BigDecimal(1.0), Currency.getInstance(Locale.getDefault()));
		// When
		final boolean result = fixture.isZero();

		// Then
		assertThat(result, equalTo(false));
	}

	/**
	 * boolean isZero() method test.
	 */
	@Test
	public void testIsZero_2() {
		final Money fixture = Money.of(new BigDecimal(1.0), Currency.getInstance(Locale.getDefault()));
		// When
		final boolean result = fixture.isZero();

		// Then
		assertThat(result, equalTo(false));
	}

	/**
	 * Money negated() method test.
	 */
	@Test
	public void testNegated_1() {
		final Money fixture = Money.of(new BigDecimal(1.0), Currency.getInstance(Locale.getDefault()));
		// When
		final Money result = fixture.negated();

		// Then
		assertThat(result, notNullValue());
		assertThat(result.isZero(), equalTo(false));
	}

	/**
	 * Money of(int) method test.
	 */
	@Test
	public void testOf_1() {
		final int amount = 1;
		// When
		final Money result = Money.of(amount);

		// Then
		assertThat(result, notNullValue());
		assertThat(result.isZero(), equalTo(false));
	}

	/**
	 * Money of(long) method test.
	 */
	@Test
	public void testOf_2() {
		final long amount = 1L;
		// When
		final Money result = Money.of(amount);

		// Then
		assertThat(result, notNullValue());
		assertThat(result.isZero(), equalTo(false));
	}

	/**
	 * Money of(BigDecimal) method test.
	 */
	@Test
	public void testOf_3() {
		final BigDecimal amount = new BigDecimal(1.0);
		// When
		final Money result = Money.of(amount);

		// Then
		assertThat(result, notNullValue());
		assertThat(result.isZero(), equalTo(false));
	}

	/**
	 * Money of(BigDecimal,Currency) method test.
	 */
	@Test
	public void testOf_4() {
		final BigDecimal amount = new BigDecimal(1.0);
		final Currency currency = Currency.getInstance(Locale.getDefault());
		// When
		final Money result = Money.of(amount, currency);

		// Then
		assertThat(result, notNullValue());
		assertThat(result.isZero(), equalTo(false));
	}

	/**
	 * Money substract(Money) method test.
	 */
	@Test
	public void testSubstract_1() {

    // // Given
    // final Money fixture = Money.of(new BigDecimal(1.0), Currency.getInstance(Locale.getDefault()));
    // final Money other = Money.zero();
    // // When
    // final Money result = fixture.substract(other);
    //
    // // Then
    // assertThat(result, notNullValue());
    // assertThat(result.isZero(), equalTo(false));
	}

	/**
	 * Money zero() method test.
	 */
	@Test
	public void for_Zero_Money_default_currency_Value_IsZero_Should_Return_True() {

		final Money result = Money.zero();

		// Then
		assertThat(result, notNullValue());
		assertThat(result.isZero(), equalTo(true));
	}

	/**
	 * Money zero(Currency) method test.
	 */
	@Test
	public void for_Zero_Money_Value_IsZero_Should_Return_True() {
		// Given
		final Currency currency = Currency.getInstance(Locale.getDefault());
		// When
		final Money result = Money.zero(currency);
		// Then
		assertThat(result, notNullValue());
		assertThat(result.isZero(), equalTo(true));
	}

}