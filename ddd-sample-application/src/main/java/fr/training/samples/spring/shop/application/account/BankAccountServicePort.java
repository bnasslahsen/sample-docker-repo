package fr.training.samples.spring.shop.application.account;

import fr.training.samples.spring.shop.domain.account.BankAccount;
import fr.training.samples.spring.shop.domain.account.Owner;
import fr.training.samples.spring.shop.domain.common.exception.InsufficientFundsException;
import fr.training.samples.spring.shop.domain.common.vo.AccountNumber;
import fr.training.samples.spring.shop.domain.common.vo.Money;

/**
 * 
 */
public interface BankAccountServicePort {

	/**
   * Create a new Bank account
   *
   * @param overDraftAmount overDraft Amount authorized
   * @param owners Bank account owners
   * @return BankAccount the bank account
   */
	BankAccount createAccount(Money overDraftAmount, Owner... owners);

	/**
	 * Get Balance money operation.
	 *
	 * @param iban
	 *            the Bank account identifier
	 * @return Balance amount
	 */
	Money getBalance(AccountNumber iban);

	/**
	 * Withdraw money operation.
	 *
	 * @param iban
	 *            the Bank account identifier
	 * @param amount
	 *            Money amount to withdraw
	 * @throws InsufficientFundsException
	 */
	void withdraw(AccountNumber iban, Money amount);

	/**
	 * Deposit money operation.
	 *
	 * @param iban
	 *            the Bank account identifier
	 * @param amount
	 *            money amount to deposit
	 * @throws InsufficientFundsException
	 */
	void deposit(AccountNumber iban, Money amount);

	/**
	 * Transfer money operation.
	 *
	 * @param fromIban
	 *            the Source Bank account identifier
	 * @param toIban
	 *            the target Bank account identifier
	 * @param amount
	 *            Money amount to transfer
	 * @throws InsufficientFundsException
	 */
	void transfer(AccountNumber fromIban, AccountNumber toIban, Money amount);

	/**
	 * Close account operation.
	 *
	 * @param iban
	 *            the Bank account identifier
	 */
	void close(AccountNumber iban);

	/**
	 * Suspend account operation.
	 *
	 * @param iban
	 *            the Bank account identifier
	 */
	void suspend(AccountNumber iban);

	/**
	 * Find BankAccount using his surrogate key.
	 *
	 * @param id
	 *            BankAccount surrogate key.
	 * @return the BankAccount if found
	 */
	BankAccount findOne(String id);

}