package fr.training.samples.spring.shop.domain.common.vo;


import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FrequencyTest {

	private final LocalDate aDate = LocalDate.of(2019, Month.JULY, 1);
	private final LocalDate anotherDate = LocalDate.of(2019, Month.MARCH, 31);

	@Test
	public void testGetNextTranfertDateMonthly() {
		final LocalDate actual = Frequency.MONTHLY.getNextTranfertDate(aDate);
		assertThat(actual, equalTo(LocalDate.of(2019, Month.AUGUST, 1)));
	}

	@Test
	public void testGetNextTranfertDateQuarterly() {
		final LocalDate actual = Frequency.QUARTERLY.getNextTranfertDate(aDate);
		assertThat(actual, equalTo(LocalDate.of(2019, Month.OCTOBER, 1)));
	}

	@Test
	public void testGetNextTranfertDateSemiAnnual() {
		final LocalDate actual = Frequency.SEMI_ANNUAL.getNextTranfertDate(aDate);
		assertThat(actual, equalTo(LocalDate.of(2020, Month.JANUARY, 1)));
	}

	@Test
	public void testGetNextTranfertDateAnnualLastDayOfMonth() {
		final LocalDate actual = Frequency.ANNUAL.getNextTranfertDate(aDate);
		assertThat(actual, equalTo(LocalDate.of(2020, Month.JULY, 1)));
	}

	@Test
	public void testGetNextTranfertDateMonthlyLastDayOfMonth() {
		final LocalDate actual = Frequency.MONTHLY.getNextTranfertDate(anotherDate);
		assertThat(actual, equalTo(LocalDate.of(2019, Month.APRIL, 30)));
	}

	@Test
	public void testGetNextTranfertDateQuarterlyLastDayOfMonth() {
		final LocalDate actual = Frequency.QUARTERLY.getNextTranfertDate(anotherDate);
		assertThat(actual, equalTo(LocalDate.of(2019, Month.JUNE, 30)));
	}

	@Test
	public void testGetNextTranfertDateSemiAnnualLastDayOfMonth() {
		final LocalDate actual = Frequency.SEMI_ANNUAL.getNextTranfertDate(anotherDate);
		assertThat(actual, equalTo(LocalDate.of(2019, Month.SEPTEMBER, 30)));
	}

	@Test
	public void testGetNextTranfertDateAnnual() {
		final LocalDate actual = Frequency.ANNUAL.getNextTranfertDate(anotherDate);
		assertThat(actual, equalTo(LocalDate.of(2020, Month.MARCH, 31)));
	}

}
