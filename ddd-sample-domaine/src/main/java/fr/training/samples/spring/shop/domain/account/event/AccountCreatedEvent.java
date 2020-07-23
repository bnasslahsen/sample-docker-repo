package fr.training.samples.spring.shop.domain.account.event;

import java.util.List;

import fr.training.samples.spring.shop.domain.account.Owner;
import fr.training.samples.spring.shop.domain.common.vo.AccountNumber;
import fr.training.samples.spring.shop.domain.common.vo.OverDraftPolicy;

/**
 * Event occured when a new Bank account was created.
 */
public class AccountCreatedEvent extends Event {

	/**
	 * Functional identifier
	 */
	private final AccountNumber accountNumber;

	/**
	 * The OverDraft policy
	 */
	private final OverDraftPolicy overDraftPolicy;

	/**
	 * Person list who own the the bank account
	 */
	private final List<Owner> owners;

	/**
	 *
	 * @param builder
	 */
	private AccountCreatedEvent(final Builder builder) {
		accountNumber = builder.accountNumber;
		overDraftPolicy = builder.overDraftPolicy;
		owners = builder.owners;
	}

	/**
	 * Builder static assessor
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * @return the accountNumber
	 */
	public AccountNumber getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @return the overDraftPolicy
	 */
	public OverDraftPolicy getOverDraftPolicy() {
		return overDraftPolicy;
	}

	/**
	 * @return the owners
	 */
	public List<Owner> getOwners() {
		return owners;
	}

	/**
	 * Builder pattern
	 */
	public static class Builder {
		private AccountNumber accountNumber;

		private OverDraftPolicy overDraftPolicy;

		private List<Owner> owners;

		public Builder accountNumber(final AccountNumber accountNumber) {
			this.accountNumber = accountNumber;
			return this;
		}

		public Builder overDraftPolicy(final OverDraftPolicy overDraftPolicy) {
			this.overDraftPolicy = overDraftPolicy;
			return this;
		}

		public Builder owners(final List<Owner> owners) {
			this.owners = owners;
			return this;
		}

		public AccountCreatedEvent build() {
			return new AccountCreatedEvent(this);
		}

	}
}