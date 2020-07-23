package fr.training.samples.spring.shop.application.account;

import java.math.BigDecimal;
import java.util.Optional;

import fr.training.samples.spring.shop.domain.account.BankAccount;
import fr.training.samples.spring.shop.domain.account.Owner;
import fr.training.samples.spring.shop.domain.account.repository.BankAccountRepositoryPort;
import fr.training.samples.spring.shop.domain.accountnumber.repository.AccountNumberServicePort;
import fr.training.samples.spring.shop.domain.common.exception.InsufficientFundsException;
import fr.training.samples.spring.shop.domain.common.exception.NotZeroBalanceException;
import fr.training.samples.spring.shop.domain.common.vo.AccountNumber;
import fr.training.samples.spring.shop.domain.common.vo.AccountStatus;
import fr.training.samples.spring.shop.domain.common.vo.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

/**
 * Simple JUnit Test for BankAccountService.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BankAccountServiceTest {

	private static final Money ZERO_BALANCE = Money.zero();

	private static final AccountNumber IBAN_INSUFICIENT = AccountNumber.of("FR1830004600401891840058020");

	private static final AccountNumber IBAN_DEBIT = AccountNumber.of("FR4830004509770640790476899");

	private static final AccountNumber IBAN_CREDIT = AccountNumber.of("FR8030004338226226651763205");

	@Autowired
	private BankAccountServicePort accountService;

	@MockBean
	private BankAccountRepositoryPort accountRepositoryMock;

	@MockBean
	private AccountNumberServicePort accountNumberServiceMock;

	private BankAccount insufficientAccount;

	private Optional<BankAccount> optInsufficientAccount;

	private BankAccount debitAccount;

	private Optional<BankAccount> optDebitAccount;

	private BankAccount creditAccount;

	private Optional<BankAccount> optCreditAccount;

	@Test
	public void contextLoads() {
		assertNotNull(accountService);
	}

	@Before
	public void initAccounts() {
		insufficientAccount = new BankAccount.Builder() //
				.accountNumber(IBAN_INSUFICIENT) //
				.overdraftProtection(ZERO_BALANCE) //
				.addOwner(Owner.builder().firstName("John").lastName("Doe").email("john.doe@gmail.com").build())
				.build();
		optInsufficientAccount = Optional.ofNullable(insufficientAccount);
		debitAccount = new BankAccount.Builder() //
				.accountNumber(IBAN_DEBIT) //
				.overdraftProtection(ZERO_BALANCE) //
				.addOwner(Owner.builder().firstName("John").lastName("Doe").email("john.doe@gmail.com").build())
				.build();
		debitAccount.credit(Money.of(BigDecimal.valueOf(1000), Money.EUR));
		optDebitAccount = Optional.ofNullable(debitAccount);
		creditAccount = new BankAccount.Builder() //
				.accountNumber(IBAN_CREDIT) //
				.overdraftProtection(ZERO_BALANCE) //
				.addOwner(Owner.builder().firstName("John").lastName("Doe").email("john.doe@gmail.com").build())
				.build();
		creditAccount.credit(Money.of(BigDecimal.valueOf(500), Money.EUR));
		optCreditAccount = Optional.ofNullable(creditAccount);
	}

	@Test
	public void testWithdrawShouldSuccess() {
		when(accountRepositoryMock.findByAccountNumber(IBAN_DEBIT)).thenReturn(optDebitAccount);
		accountService.withdraw(IBAN_DEBIT, Money.of(BigDecimal.valueOf(500), Money.EUR));
		assertThat(debitAccount.getBalance(), equalTo(Money.of(BigDecimal.valueOf(500), Money.EUR)));
	}

	@Test
	public void testTransferShouldSuccess() {
		when(accountRepositoryMock.findByAccountNumber(IBAN_DEBIT)).thenReturn(optDebitAccount);
		when(accountRepositoryMock.findByAccountNumber(IBAN_CREDIT)).thenReturn(optCreditAccount);
		accountService.transfer(IBAN_DEBIT, IBAN_CREDIT, Money.of(BigDecimal.valueOf(100), Money.EUR));
		assertThat(debitAccount.getBalance(), equalTo(Money.of(BigDecimal.valueOf(900), Money.EUR)));
		assertThat(creditAccount.getBalance(), equalTo(Money.of(BigDecimal.valueOf(600), Money.EUR)));
	}

	@Test(expected = InsufficientFundsException.class)
	public void testWithdrawInsufficientBalanceShouldFails() throws Exception {
		when(accountRepositoryMock.findByAccountNumber(IBAN_INSUFICIENT)).thenReturn(optInsufficientAccount);
		accountService.withdraw(IBAN_INSUFICIENT, Money.of(500));
		fail("InsufficientBalanceException expected here");
	}

	@Test(expected = NotZeroBalanceException.class)
	public void testCloseNotZeroBalanceShouldFails() {
		when(accountRepositoryMock.findByAccountNumber(IBAN_DEBIT)).thenReturn(optDebitAccount);
		accountService.close(IBAN_DEBIT);
		fail("NotZeroBalanceException expected here");
	}

	@Test
	public void testCloseShouldSuccess() {
		when(accountRepositoryMock.findByAccountNumber(IBAN_INSUFICIENT)).thenReturn(optInsufficientAccount);
		accountService.close(IBAN_INSUFICIENT);
		assertThat(insufficientAccount.getStatus(), equalTo(AccountStatus.CLOSED));
	}

	@Test
	public void testSuspendShouldSuccess() {
		when(accountRepositoryMock.findByAccountNumber(IBAN_DEBIT)).thenReturn(optDebitAccount);
		accountService.suspend(IBAN_DEBIT);
		assertThat(debitAccount.getStatus(), equalTo(AccountStatus.SUSPENDED));
	}

}