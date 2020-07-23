package fr.training.samples.spring.shop.domain.common.vo;

import fr.training.samples.spring.shop.domain.account.BankAccount;
import fr.training.samples.spring.shop.domain.account.Owner;
import fr.training.samples.spring.shop.domain.common.exception.InsufficientFundsException;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Architecture logicielle
 */
public class OverDraftPolicyTest {

	private final BankAccount zeroBalance = new BankAccount.Builder() //
			.accountNumber(AccountNumber.of("FR8030004338226226651763205")) //
			.addOwner(Owner.builder().firstName("John").lastName("Doe").email("john.doe@gmail.com").build())
			.overdraftProtection(Money.of(100))//
			.build();

	private final BankAccount positiveBalance = new BankAccount.Builder() //
			.accountNumber(AccountNumber.of("FR8030004338226226651763205")) //
			.addOwner(Owner.builder().firstName("Jane").lastName("Doe").email("jane.doe@gmail.com").build())
			.overdraftProtection(Money.of(100)) //
			.build();

	private final BankAccount negativeBalance = new BankAccount.Builder() //
			.accountNumber(AccountNumber.of("FR8030004338226226651763205")) //
			.addOwner(Owner.builder().firstName("Jack").lastName("Doe").email("jake.doe@gmail.com").build())
			.overdraftProtection(Money.of(100))//
			.build();

	@Before
	public void setUp() {
		positiveBalance.credit(Money.of(150));
		negativeBalance.debit(Money.of(50));
	}

	@Test(expected = InsufficientFundsException.class)
	public void preDebitZeroBalanceNoOverdraftShouldFail() {
		OverDraftPolicy.NO_OVERDRAFT.preDebit(zeroBalance, Money.of(100));
	}

	@Test
	public void preDebitPositiveBalanceNoOverdraftShouldSuccess() {
		OverDraftPolicy.NO_OVERDRAFT.preDebit(positiveBalance, Money.of(100));
	}

	@Test(expected = InsufficientFundsException.class)
	public void preDebitNegativeBalanceNoOverdraftShouldFail() {
		OverDraftPolicy.NO_OVERDRAFT.preDebit(negativeBalance, Money.of(100));
	}

	@Test
	public void preDebitZeroBalanceLimitedOverdraftShouldSuccess() {
		OverDraftPolicy.LIMITED.preDebit(zeroBalance, Money.of(100));
	}

	@Test(expected = InsufficientFundsException.class)
	public void preDebitNegativetiveBalanceLimitedOverdraftShouldFail() {
		OverDraftPolicy.LIMITED.preDebit(negativeBalance, Money.of(100));
	}

	@Test
	public void preDebitPositiveBalanceLimitedOverdraftShouldSuccess() {
		OverDraftPolicy.LIMITED.preDebit(positiveBalance, Money.of(100));
	}

}
