package fr.training.samples.spring.shop.infrastructure.accountnumber.repository;

import fr.training.samples.spring.shop.domain.accountnumber.repository.AccountNumberServicePort;
import fr.training.samples.spring.shop.domain.common.vo.AccountNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Client for generateAccountNumber central service (simulate zOSConnect call).
 */
@Profile({ "dev", "qualif", "prod" })
@Component
public class AccountNumberServiceAdapter implements AccountNumberServicePort {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountNumberServiceAdapter.class);

	@Value("${account-number.service.endpoint}")
	private String accountNumberServiceEndPoint;

	private final RestTemplate restTemplate;

	public AccountNumberServiceAdapter() {
		restTemplate = new RestTemplate();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.bnpparibas.dsibddf.bank.account.app.service.AccountNumberServicePort#
	 * createNewAccountNumber()
	 */
	@Override
	public AccountNumber createNewAccountNumber() {
		LOGGER.debug("calling external accountNumber Service");

		final ResponseEntity<AccountNumberDto> response = restTemplate
				.getForEntity(accountNumberServiceEndPoint + "/generateAccountNumber", AccountNumberDto.class);

		final String newAccountNumber = response.getBody().getValue();
		LOGGER.info("Get new account number {} from central system call", newAccountNumber);

		return AccountNumber.of(newAccountNumber);
	}
}
