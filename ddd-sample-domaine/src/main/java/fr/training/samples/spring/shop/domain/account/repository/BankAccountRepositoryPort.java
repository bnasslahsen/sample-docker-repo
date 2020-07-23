package fr.training.samples.spring.shop.domain.account.repository;

import java.util.Optional;

import fr.training.samples.spring.shop.domain.account.BankAccount;
import fr.training.samples.spring.shop.domain.common.vo.AccountNumber;

/**
 * Interface for BankAccount Repository
 */
public interface BankAccountRepositoryPort {

	/**
	 * Save bank account.
	 *
	 * @param account
	 *            Bank Account to Save
	 * @return Saved bank account
	 */
	public BankAccount save(BankAccount account);

	/**
	 * Find bank account by his number.
	 *
	 * @param accountNumber
	 *
	 * @return the Bank Account if found
	 */
	public Optional<BankAccount> findByAccountNumber(final AccountNumber accountNumber);

	/**
	 * Find bank account by his id.
	 * 
	 * @param id
	 *            bank account
	 * @return the bank account if found
	 */
	public BankAccount findOne(String id);

}
