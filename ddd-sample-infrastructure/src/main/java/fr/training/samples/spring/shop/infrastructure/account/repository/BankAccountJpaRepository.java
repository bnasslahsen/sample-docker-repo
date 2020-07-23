package fr.training.samples.spring.shop.infrastructure.account.repository;


import java.util.Optional;

import fr.training.samples.spring.shop.domain.account.BankAccount;
import fr.training.samples.spring.shop.domain.common.vo.AccountNumber;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BankAccount JPA repository
 */
public interface BankAccountJpaRepository extends JpaRepository<BankAccount, String> {

	Optional<BankAccount> findByAccountNumber(AccountNumber accountNumber);

}
