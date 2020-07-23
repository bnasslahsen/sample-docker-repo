package fr.training.samples.spring.shop.domain.common.vo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AccountNumberTest {

	// ISO Code for France
	private static final String COUNTRY_CODE = "FR";

	// IBAN check digits
	private static final String CHECK_DIGITS = "76";

	// Bank Code
	private static final String BANK_CODE = "30004";

	// Branch Code (Code Guichet)
	private static final String BRANCH_CODE = "80632";

	// BBAN
	private static final String BBAN = "89386975731";

	// National check digits (clé RIB)
	private static final String NATIONAL_CHECK_DIGITS = "38";

	private static final String VALID_IBAN0 = COUNTRY_CODE + CHECK_DIGITS + BANK_CODE + BRANCH_CODE + BBAN
			+ NATIONAL_CHECK_DIGITS;
	private static final String INVALID_IBAN1 = "AL36442788709271283994894168";
	private static final String VALID_IBAN2 = "FR7630004955052077379731236";

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testOfWithValidIBANShouldSuccess() {
		assertThat(AccountNumber.of(VALID_IBAN2).getValue(), equalTo(VALID_IBAN2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOfInvalidIBANShouldFail() {
		assertThat(AccountNumber.of(INVALID_IBAN1), nullValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOfBadFormatIBANShouldFail() {
		assertThat(AccountNumber.of(BBAN), nullValue());
	}

	@Test
	public void testGetValueWithValidIBANShouldSuccess() {
		assertThat(AccountNumber.of(VALID_IBAN0).getValue(), equalTo(VALID_IBAN0));
	}

	@Test
	public void testEqualsObject() {
		assertTrue(AccountNumber.of(VALID_IBAN2).equals(AccountNumber.of(VALID_IBAN2)));
		assertFalse(AccountNumber.of(VALID_IBAN0).equals(AccountNumber.of(VALID_IBAN2)));
	}

	@Test
	public void testToString() {
		assertThat(AccountNumber.of(VALID_IBAN0), hasToString("AccountNumber[" + VALID_IBAN0 + "]"));
	}

}
