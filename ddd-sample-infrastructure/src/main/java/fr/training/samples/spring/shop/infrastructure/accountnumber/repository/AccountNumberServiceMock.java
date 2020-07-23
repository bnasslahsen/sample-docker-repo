package fr.training.samples.spring.shop.infrastructure.accountnumber.repository;

import java.util.Arrays;
import java.util.List;

import fr.training.samples.spring.shop.domain.accountnumber.repository.AccountNumberServicePort;
import fr.training.samples.spring.shop.domain.common.vo.AccountNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Mock Client for generateAccountNumber central service (simulate zOSConnect
 * call).
 */
@Profile({ "test", "local" })
@Component
public class AccountNumberServiceMock implements AccountNumberServicePort {

	private static final String MOCK_IBAN = "FR7630004250426062439668469";

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountNumberServiceAdapter.class);

	private final List<String> list = Arrays.asList("FR1030004096910384684503644", "FR0630004227392330913861105",
			"FR8030004338226226651763205", "FR4830004509770640790476899", "FR5630004699197219479770990",
			"FR6630004590279710694492260", "FR1830004600401891840058020", "FR8330004104722870121863515",
			"FR8930004652212674284020379", "FR3930004141152391622257775");

	private int i = 0;

	@Override
	public AccountNumber createNewAccountNumber() {
		LOGGER.warn("Use Mock AccountNumber generator : use {}", MOCK_IBAN);
		// return always the same AccountNumber for tests
		return AccountNumber.of(list.get(i++));
	}
}
