package fr.training.samples.spring.shop.domain.accountnumber.repository;

import fr.training.samples.spring.shop.domain.common.vo.AccountNumber;

/**
 * Repository for {@link AccountNumber}
 */
public interface AccountNumberServicePort {

	public AccountNumber createNewAccountNumber();

}
