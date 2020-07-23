package fr.training.samples.spring.shop.infrastructure.account.repository;

import java.util.Optional;

import fr.training.samples.spring.shop.domain.account.BankAccount;
import fr.training.samples.spring.shop.domain.account.repository.BankAccountRepositoryPort;
import fr.training.samples.spring.shop.domain.common.vo.AccountNumber;

import org.springframework.stereotype.Component;

@Component
public class BankAccountRepositoryAdapter implements BankAccountRepositoryPort {

	private final BankAccountJpaRepository bankAccountJpaRepository;

	/**
	 * Constructor for bean injection
	 */
	public BankAccountRepositoryAdapter(final BankAccountJpaRepository bankAccountJpaRepository) {
		this.bankAccountJpaRepository = bankAccountJpaRepository;
	}

	/*
	 * (non-Javadoc)
	 *
	 */
	@Override
	public Optional<BankAccount> findByAccountNumber(final AccountNumber accountNumber) {

		return bankAccountJpaRepository.findByAccountNumber(accountNumber);
	}

	/*
	 * (non-Javadoc)
	 *
	 */
	@Override
	public BankAccount save(final BankAccount account) {

		return bankAccountJpaRepository.save(account);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.training.samples.spring.shop.domain.account.repository.
	 * BankAccountRepositoryPort#findOne(java.lang.Long)
	 */
	@Override
	public BankAccount findOne(final String id) {

		return bankAccountJpaRepository.getOne(id);
	}

}
