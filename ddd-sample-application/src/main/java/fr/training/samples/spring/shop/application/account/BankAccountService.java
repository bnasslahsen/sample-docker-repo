package fr.training.samples.spring.shop.application.account;

import java.util.Optional;

import fr.training.samples.spring.shop.domain.account.BankAccount;
import fr.training.samples.spring.shop.domain.account.Owner;
import fr.training.samples.spring.shop.domain.account.repository.BankAccountRepositoryPort;
import fr.training.samples.spring.shop.domain.accountnumber.repository.AccountNumberServicePort;
import fr.training.samples.spring.shop.domain.common.exception.InvalidAccountNumberException;
import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.common.vo.AccountNumber;
import fr.training.samples.spring.shop.domain.common.vo.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service orchestration for BankAccount operations.
 */
@Service
@Transactional
public class BankAccountService implements BankAccountServicePort {

	private static final Logger LOG = LoggerFactory.getLogger(BankAccountService.class);

	private final BankAccountRepositoryPort accountRepository;

	private final AccountNumberServicePort accountNumberService;

	/**
	 * Contructor for Bean injection
	 */
	public BankAccountService(final BankAccountRepositoryPort accountRepository,
			final AccountNumberServicePort accountNumberService) {
		this.accountRepository = accountRepository;
		this.accountNumberService = accountNumberService;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see fr.training.samples.spring.shop.application.account.
	 * BankAccountServicePort#createAccount(fr.training.samples.spring.shop.domain.common.vo.Money)
	 */
	@Override
	public BankAccount createAccount(final Money overDraftAmount, final Owner... owners) {
		LOG.debug("createAccount with overDraftAmount {}", overDraftAmount);
		final BankAccount account = new BankAccount.Builder() //
				.accountNumber(accountNumberService.createNewAccountNumber()) //
				.overdraftProtection(overDraftAmount) //
				.owners(owners) //
				.build();

		accountRepository.save(account);

		return account;
	}

	/**
	 * Find {@link BankAccount} from repository
	 *
	 * @param iban
	 *            {@link BankAccount} business identifier
	 * @return the Bank account if found else throw {@link NotFoundException}
	 */
	private BankAccount findAccount(final AccountNumber iban) {
		final Optional<BankAccount> accountFound = accountRepository.findByAccountNumber(iban);

		return accountFound.orElseThrow(() -> new NotFoundException("Account " + iban + " was not found"));
	}

	/*
	 * (non-Javadoc)
	 *
	 */
	@Override
	public Money getBalance(final AccountNumber iban) {
		LOG.debug("getBalance for accountNumber {}", iban);

		final BankAccount account = findAccount(iban);

		return account.getBalance();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see fr.training.samples.spring.shop.application.account.
	 * BankAccountServicePort#withdraw(fr.training.samples.spring.shop.domain.common.vo.AccountNumber,
	 * Money)
	 */
	@Override
	public void withdraw(final AccountNumber iban, final Money amount) {
		LOG.debug("withdraw amount of {} for accountNumber {}", amount, iban);
		final BankAccount account = findAccount(iban);

		account.debit(amount);
		accountRepository.save(account);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see fr.training.samples.spring.shop.application.account.
	 * BankAccountServicePort#deposit(fr.training.samples.spring.shop.common.vo.AccountNumber, Money)
	 */
	@Override
	public void deposit(final AccountNumber iban, final Money amount) {
		LOG.debug("deposit amount of {} for accountNumber {}", amount, iban);
		// Find bank account by using iban
		final BankAccount account = findAccount(iban);

		account.credit(amount);
		accountRepository.save(account);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see fr.training.samples.spring.shop.application.account.
	 * BankAccountServicePort#transfer(fr.training.samples.spring.shop.common.vo.AccountNumber,
	 * fr.training.samples.spring.shop.domain.common.vo.
	 * AccountNumber,
	 * Money)
	 */
	@Override
	public void transfer(final AccountNumber fromIban, final AccountNumber toIban, final Money amount) {

		LOG.debug("transfer amount of {} from accountNumber {} to {} ", amount, fromIban, toIban);
		final BankAccount fromAccount = findAccount(fromIban);
		final BankAccount toAccount = findAccount(toIban);
		if (fromAccount.equals(toAccount)) {
			throw new InvalidAccountNumberException("Transfert to account itself is not allowed");
		}
		fromAccount.transfer(toAccount, amount);

		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
	}

	/*
	 * (non-Javadoc)
	 *
	 */
	@Override
	public void close(final AccountNumber iban) {
		LOG.debug("Close account for number {}", iban);
		// Find bank account by using iban
		final BankAccount account = findAccount(iban);

		account.close();
		accountRepository.save(account);
	}

	/*
	 * (non-Javadoc)
	 *
	 */
	@Override
	public void suspend(final AccountNumber iban) {
		LOG.debug("Suspend account for number {}", iban);
		// Find bank account by using iban
		final BankAccount account = findAccount(iban);

		account.suspend();
		accountRepository.save(account);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see fr.training.samples.spring.shop.application.account.
	 * BankAccountServicePort#findOne(java.lang.String)
	 */
	@Override
	public BankAccount findOne(final String id) {
		return accountRepository.findOne(id);
	}

}