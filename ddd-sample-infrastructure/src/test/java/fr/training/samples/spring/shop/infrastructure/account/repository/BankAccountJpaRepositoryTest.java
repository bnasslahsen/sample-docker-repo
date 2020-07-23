package fr.training.samples.spring.shop.infrastructure.account.repository;

import java.math.BigDecimal;
import java.util.Optional;

import fr.training.samples.spring.shop.domain.account.BankAccount;
import fr.training.samples.spring.shop.domain.account.Owner;
import fr.training.samples.spring.shop.domain.common.vo.AccountNumber;
import fr.training.samples.spring.shop.domain.common.vo.AccountStatus;
import fr.training.samples.spring.shop.domain.common.vo.Money;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Simple JUnit Test for {@link BankAccountRepository}.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class BankAccountJpaRepositoryTest {

	private static final AccountNumber IBAN_1 = AccountNumber.of("FR1030004096910384684503644");

	private static final AccountNumber IBAN_2 = AccountNumber.of("FR0630004227392330913861105");

	private static final AccountNumber IBAN_3 = AccountNumber.of("FR8030004338226226651763205");

	@Autowired
	private BankAccountRepositoryAdapter bankAccountRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testFindByIbanExistingShouldSuccess() {
		// Given
		final BankAccount account = new BankAccount.Builder() //
				.accountNumber(IBAN_1) //
				.overdraftProtection(Money.zero()) //
				.addOwner(Owner.builder().firstName("John").lastName("Doe").email("john.doe@gmail.com").build())
				.build();
		account.credit(Money.of(BigDecimal.valueOf(500), Money.EUR));
		entityManager.persistAndFlush(account);
		// When
		final Optional<BankAccount> found = bankAccountRepository.findByAccountNumber(IBAN_1);
		// Then
		assertThat(found.isPresent(), is(true));
	}

	@Test
	public void testFindByIbanUnknownShouldFail() {

		final Optional<BankAccount> found = bankAccountRepository.findByAccountNumber(IBAN_3);
		assertThat(found.isPresent(), is(false));
	}

	@Test
	public void testCreateAndFindShouldSuccess() {
		// Given
		final BankAccount account = new BankAccount.Builder() //
				.accountNumber(IBAN_2) //
				.overdraftProtection(Money.zero()) //
				.addOwner(Owner.builder().firstName("John").lastName("Doe").email("john.doe@gmail.com").build())
				.build();

		account.credit(Money.of(BigDecimal.valueOf(600), Money.EUR));
		final BankAccount created = bankAccountRepository.save(account);
		// When
		final BankAccount found = bankAccountRepository.findOne(created.getId());
		// Then
		assertThat(found, is(notNullValue()));
		assertThat(found.getOverdraftProtection(), equalTo(Money.zero()));
		assertThat(found.getBalance(), equalTo(Money.of(BigDecimal.valueOf(600), Money.EUR)));
		assertThat(found.getAccountNumber(), equalTo(IBAN_2));
		assertThat(found.getStatus(), equalTo(AccountStatus.ACTIVE));
		assertThat(found.getHistory(), is(notNullValue()));
		assertThat(found.getHistory().getBalance(), equalTo(Money.zero()));
	}

}
