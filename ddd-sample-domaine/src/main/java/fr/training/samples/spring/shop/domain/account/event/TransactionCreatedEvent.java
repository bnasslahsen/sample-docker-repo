package fr.training.samples.spring.shop.domain.account.event;

import fr.training.samples.spring.shop.domain.common.vo.Money;

/**
 * Event occured when a new Transaction against a Bank Account was created.
 */
public class TransactionCreatedEvent extends Event {

	/**
	 * Transaction Type : Debit / Credit
	 */
	private final TransactionType transactionType;

	/**
	 * Transaction Money Amount
	 */
	private final Money transactionAmount;

	/**
	 * @return the transactionType
	 */
	public TransactionType getTransactionType() {
		return transactionType;
	}

	/**
	 * @return the transactionAmount
	 */
	public Money getTransactionAmount() {
		return transactionAmount;
	}

	/**
	 * Private constructor to enforce Builder usage
	 */
	private TransactionCreatedEvent(final Builder builder) {
		transactionType = builder.transactionType;
		transactionAmount = builder.transactionAmount;
	}

	/**
	 * Builder static assessor
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder pattern to ensure TransactionCreatedEvent is immutable.
	 */
	public static class Builder {
		private TransactionType transactionType;
		private Money transactionAmount;

		public Builder transactionType(final TransactionType transactionType) {
			this.transactionType = transactionType;
			return this;
		}

		public Builder transactionAmount(final Money transactionAmount) {
			this.transactionAmount = transactionAmount;
			return this;
		}

		public TransactionCreatedEvent build() {
			return new TransactionCreatedEvent(this);
		}
	}

}
