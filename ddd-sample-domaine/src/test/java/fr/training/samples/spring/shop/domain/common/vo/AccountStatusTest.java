package fr.training.samples.spring.shop.domain.common.vo;

import fr.training.samples.spring.shop.domain.common.exception.IllegalAccountStatusException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Junit test for AccountStatus Value Object
 */
public class AccountStatusTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	private AccountStatus status;

	@Test
	public void activeToSuspendedTransitionShouldSuccess() {
		status = AccountStatus.ACTIVE;

		status = status.transition(AccountStatus.SUSPENDED);

		assertEquals(AccountStatus.SUSPENDED, status);
	}

	@Test(expected = IllegalAccountStatusException.class)
	public void activeToActiveTransitionShouldFail() {
		AccountStatus state = AccountStatus.ACTIVE;

		state = state.transition(AccountStatus.ACTIVE);
	}

	@Test
	public void activeToClosedTransitionShouldSuccess() {

		status = AccountStatus.ACTIVE;

		status = status.transition(AccountStatus.CLOSED);

		assertEquals(AccountStatus.CLOSED, status);
	}

	@Test(expected = IllegalAccountStatusException.class)
	public void closedToSuspendedTransitionShouldFail() {
		AccountStatus state = AccountStatus.CLOSED;

		state = state.transition(AccountStatus.SUSPENDED);
	}

	@Test(expected = IllegalAccountStatusException.class)
	public void closedToActiveTransitionShouldFail() {
		AccountStatus state = AccountStatus.CLOSED;

		state = state.transition(AccountStatus.ACTIVE);
	}

	@Test(expected = IllegalAccountStatusException.class)
	public void suspendedToActiveTransitionShouldFail() {
		AccountStatus state = AccountStatus.SUSPENDED;

		state = state.transition(AccountStatus.ACTIVE);
	}

	@Test(expected = IllegalAccountStatusException.class)
	public void suspendedToSuspendedTransitionShouldFail() {
		AccountStatus state = AccountStatus.SUSPENDED;

		state = state.transition(AccountStatus.ACTIVE);
	}
}