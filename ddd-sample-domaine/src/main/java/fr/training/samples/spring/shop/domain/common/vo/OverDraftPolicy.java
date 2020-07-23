package fr.training.samples.spring.shop.domain.common.vo;

import fr.training.samples.spring.shop.domain.account.BankAccount;
import fr.training.samples.spring.shop.domain.common.exception.InsufficientFundsException;

/**
 * OverDraft enumeration that implements strategy pattern.
 *
 * This pattern follow the single responsibility principle (manage OverDraft protection)
 * and allow decrease Cyclomatic Complexity.
 */
public enum OverDraftPolicy {

	LIMITED {

		@Override
		public void preDebit(final BankAccount bankAccount, final Money amount) {
			final Money protection = bankAccount.getOverdraftProtection();

			final Money newBalance = bankAccount.getBalance().substract(amount);

			if (newBalance.isLessThan(protection.negated())) {
				throw new InsufficientFundsException(
						String.format("OverDraft protection %s exceeded", protection.getAmount()));
			}
		}

		@Override
		public void postDebit(final BankAccount bankAccount, final Money money) {
		}

	},

	NO_OVERDRAFT {

		@Override
		public void preDebit(final BankAccount bankAccount, final Money amount) {

			final Money newBalance = bankAccount.getBalance().substract(amount);
			if (newBalance.isLessThan(Money.zero(bankAccount.getAccountCurrency()))) {
				throw new InsufficientFundsException(String.format("Insufficient total balance %s", bankAccount.getBalance().getAmount()));
			}
		}

		@Override
		public void postDebit(final BankAccount bankAccount, final Money money) {

		}

	};

	public abstract void preDebit(BankAccount bankAccount, Money amount);

	public abstract void postDebit(BankAccount bankAccount, Money amount);
}