package fr.training.samples.spring.shop.domain.account;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import fr.training.samples.spring.shop.domain.account.event.AccountCreatedEvent;
import fr.training.samples.spring.shop.domain.account.event.Event;
import fr.training.samples.spring.shop.domain.account.event.EventAware;
import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;
import fr.training.samples.spring.shop.domain.common.exception.InsufficientFundsException;
import fr.training.samples.spring.shop.domain.common.exception.InvalidAccountStatusException;
import fr.training.samples.spring.shop.domain.common.exception.NotZeroBalanceException;
import fr.training.samples.spring.shop.domain.common.vo.AccountNumber;
import fr.training.samples.spring.shop.domain.common.vo.AccountStatus;
import fr.training.samples.spring.shop.domain.common.vo.Money;
import fr.training.samples.spring.shop.domain.common.vo.OverDraftPolicy;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class of bank accounts (Entity).
 */
public class BankAccount extends AbstractBaseEntity implements EventAware {

	private static final Logger LOG = LoggerFactory.getLogger(BankAccount.class);

	/**
	 * Maintain event list of Event occured on aggregate.
	 */
	private transient List<Event> events = new ArrayList<Event>();

	/**
	 * Business identifier kwown by external provider
	 */
	private AccountNumber accountNumber;

	/**
	 * The balance of this account
	 */
	private Money balance;

	/**
	 * The OverDraft policy
	 */
	private OverDraftPolicy overDraftPolicy;

	/**
	 * The OverDraft policy amount
	 */
	private Money overdraftProtection;

	private History history; // The history chained list of this account

	/**
	 * Status of this account
	 */
	private AccountStatus status;

	/**
	 * Creation date.
	 */
	private LocalDate createdDate;

	/**
	 * Person list who own the account
	 */
	private final List<Owner> owners = new ArrayList<Owner>();

	/**
	 * Default constructor required for JPA
	 */
	public BankAccount() {
		super();
	}

	/**
	 * Private constructor to enforce Builder usage.
	 */
	private BankAccount(final Builder builder) {
		accountNumber = builder.accountNumber;
		overdraftProtection = builder.overdraftProtectionAmount;

		createdDate = LocalDate.now();
		for (final Owner owner : builder.owners) {
			owners.add(owner);
		}
		// Initialize default values here
		balance = Money.zero(getAccountCurrency());
		overDraftPolicy = builder.overDraftPolicy;
		status = AccountStatus.ACTIVE;

		// Add New AccountCreated event
		events.add(AccountCreatedEvent.builder() //
				.accountNumber(accountNumber) //
				.overDraftPolicy(overDraftPolicy) //
				.owners(owners) //
				.build());
	}

	/**
	 * The balance of this account.
	 */
	public Money getBalance() {
		return Money.of(balance.getAmount(), balance.getCurrency());
	}

	/**
	 * The history list of this account.
	 */
	public History getHistory() {
		return history;
	}

	/**
	 * The status of this account.
	 */
	public AccountStatus getStatus() {
		return status;
	}

	/**
	 * Create Date of this account.
	 */
	public LocalDate getCreatedDate() {
		return createdDate;
	}

	/**
	 * The overdraft protection amount of this account.
	 */
	public Money getOverdraftProtection() {
		return overdraftProtection;
	}

	/**
	 * International Bank Account Number
	 */
	public AccountNumber getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @return the currency
	 */
	public Currency getAccountCurrency() {
		return overdraftProtection.getCurrency();
	}

	/**
	 * @return the overDraftPolicy
	 */
	public OverDraftPolicy getOverDraftPolicy() {
		return overDraftPolicy;
	}

	/**
	 * Credits this account with the specified amount.
	 *
	 * @param amount
	 *            amount to credit
	 */
	public void credit(final Money amount) {
		LOG.debug("Account {}, Credit operation : {}", accountNumber, amount);
		if (isClosed()) {
			throw new InvalidAccountStatusException("This accound is close");
		}

		history = new History(balance, getHistory(), LocalDate.now());
		balance = balance.add(amount);
	}

	/**
	 * Method used to substract the given amount from account balance
	 *
	 * @param amount
	 *            amount to debit
	 * @throws InsufficientFundsException
	 *             if amout is greater than balance
	 */
	public void debit(final Money amount) {
		LOG.debug("Account {}, Debit operation : {}", accountNumber, amount);
		if (!isActive()) {
			throw new InvalidAccountStatusException("This accound is not active");
		}

		overDraftPolicy.preDebit(this, amount);

		history = new History(balance, getHistory(), LocalDate.now());
		balance = balance.substract(amount);

		overDraftPolicy.postDebit(this, amount);
	}

	/**
	 * Method used to transfer money amount from this account to the given amount
	 *
	 * @param targetAccount
	 *            account to be credited
	 * @param amount
	 *            amount to transfer
	 */
	public void transfer(final BankAccount targetAccount, final Money amount) {
		LOG.debug("Transfert {} from {} to {}", amount, accountNumber, targetAccount);
		debit(amount);
		targetAccount.credit(amount);
	}

	/**
	 * Cancels the last credit or debit operation.
	 */
	public void cancel() {
		balance = history.getBalance();
		history = history.getPrevious();
	}

	/**
	 * @return true if the Account Status is Active.
	 */
	public boolean isActive() {
		return AccountStatus.ACTIVE.equals(status);
	}

	/**
	 * Close the account : every operations are forbiden
	 */
	public void close() {
		if (!getBalance().isZero()) {
			throw new NotZeroBalanceException("Closing account expect zero balance");
		}
		status = status.transition(AccountStatus.CLOSED);
	}

	/**
	 * @return true if the Account status is Closed.
	 */
	public boolean isClosed() {
		return AccountStatus.CLOSED.equals(status);
	}

	/**
	 * Suspend the account : debit operations are forbiden.
	 */
	public void suspend() {
		status = status.transition(AccountStatus.SUSPENDED);
	}

	/**
	 * @return true if the Account status is Suspended.
	 */
	public boolean isSuspended() {
		return AccountStatus.SUSPENDED.equals(status);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37) //
				.append(getAccountNumber()) //
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
		final BankAccount other = (BankAccount) obj;

		return new EqualsBuilder() // Equality on the business reference only
				.append(accountNumber, other.accountNumber) //
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
				.append(accountNumber).append(balance) //
				.append(overdraftProtection) //
				.append(history) //
				.append(status) //
				.append(createdDate) //
				.append(owners) //
				.toString();
	}

	/**
	 * Builder static assessor
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder pattern to ensure BankAccount object is immutable.
	 */
	public static class Builder {

		private AccountNumber accountNumber;

		private Money overdraftProtectionAmount;

		// No overDraft By default
		private OverDraftPolicy overDraftPolicy = OverDraftPolicy.NO_OVERDRAFT;

		private List<Owner> owners;

		/**
		 * Sets the Account Number to the specified value.
		 */
		public Builder accountNumber(final AccountNumber accountNumber) {
			this.accountNumber = accountNumber;
			return this;
		}

		/**
		 * Sets the overdraftProtection to the specified value.
		 */
		public Builder overdraftProtection(final Money overdraftProtection) {
			if (overdraftProtection.isGreaterThan(Money.zero())) {
				overDraftPolicy = OverDraftPolicy.LIMITED;
			}
			overdraftProtectionAmount = overdraftProtection;
			return this;
		}

		/**
		 * Add a new Owner
		 */
		public Builder addOwner(final Owner owner) {
			if (owners == null) {
				owners = new ArrayList<Owner>();
			}
			owners.add(owner);
			return this;
		}

		/**
		 * Add a owner list
		 */
		public Builder owners(final Owner... owners) {
			for (final Owner owner : owners) {
				addOwner(owner);
			}
			return this;
		}

		public BankAccount build() {
			Validate.notNull(accountNumber, "Account number cannot be null!");
			Validate.notNull(overDraftPolicy, "OverDraft policy Number cannot be null!");
			Validate.notNull(overdraftProtectionAmount, "OverDraft protection amount Number cannot be null!");
			Validate.isTrue(owners != null && owners.size() > 0, "Account must have et least one owner");
			return new BankAccount(this);
		}

	}

	@Override
	public List<Event> getEvents() {
		return events;
	}

	@Override
	public void clearEvents() {
		events.clear();
	}

}