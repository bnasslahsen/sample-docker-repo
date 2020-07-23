package fr.training.samples.spring.shop.domain.account.steps;

import java.math.BigDecimal;
import java.util.Currency;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.training.samples.spring.shop.domain.account.BankAccount;
import fr.training.samples.spring.shop.domain.account.Owner;
import fr.training.samples.spring.shop.domain.common.exception.InsufficientFundsException;
import fr.training.samples.spring.shop.domain.common.exception.InvalidAccountStatusException;
import fr.training.samples.spring.shop.domain.common.exception.NotSupportedException;
import fr.training.samples.spring.shop.domain.common.vo.AccountNumber;
import fr.training.samples.spring.shop.domain.common.vo.Money;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Step implementation of the Feature: Account operations
 */
public class BankAccountSteps {

	private BankAccount bankAccount;

	private Exception ex;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Given("^An account \"([^\"]*)\" with Currency is \"([^\"]*)\"$")
	public void an_account_with_Currency_is(final String accountNumber, final String currency) throws Throwable {
		bankAccount = new BankAccount.Builder() //
				.accountNumber(AccountNumber.of(accountNumber)) //
				.addOwner(Owner.builder().firstName("John").lastName("Doe").email("john.doe@gmail.com").build())
				.overdraftProtection(Money.zero()) //
				.build();
	}

	@Given("^An account \"([^\"]*)\" with overdraft of (\\d+) \"([^\"]*)\"$")
	public void an_account_with_overdraft_of(final String accountNumber, final int overDraft, final String currency)
			throws Throwable {
		bankAccount = new BankAccount.Builder()
				.accountNumber(AccountNumber.of(accountNumber)) //
				.addOwner(Owner.builder().firstName("John").lastName("Doe").email("john.doe@gmail.com").build())
				.overdraftProtection(Money.of(BigDecimal.valueOf(overDraft), Currency.getInstance(currency))) //
				.build();
	}

	@Given("^a balance of (\\d+)$")
	public void a_balance_of(final int balance) throws Throwable {
		bankAccount.credit(Money.of(BigDecimal.valueOf(balance), bankAccount.getAccountCurrency()));
	}

	@When("^I withdraw (\\d+) \"([^\"]*)\"$")
	public void i_withdraw_from_account(final int amount, final String currency) throws Throwable {
		try {
			bankAccount.debit(Money.of(BigDecimal.valueOf(amount), Currency.getInstance(currency)));
		} catch (final Exception e) {
			ex = e;
		}
	}

	@When("^I deposit (\\d+) \"([^\"]*)\"$")
	public void i_deposit(final int amount, final String currency) throws Throwable {
		try {
			bankAccount.credit(Money.of(BigDecimal.valueOf(amount), Currency.getInstance(currency)));
		} catch (final Exception e) {
			ex = e;
		}
	}

	@Then("^Account \"([^\"]*)\" has balance (-?\\d+)$")
	public void account_has_balance(final String accountNumber, final BigDecimal expectedBalance) throws Throwable {
		assertThat(bankAccount.getBalance(), equalTo(Money.of(expectedBalance, Money.EUR)));
	}

	@Then("^it Fails with InsufficientFundsException$")
	public void it_Fails() throws Throwable {
		assertThat(ex, instanceOf(InsufficientFundsException.class));
	}

	@Then("^it Fails with MonetaryException$")
	public void it_Fails_with_MonetaryException() throws Throwable {
		assertThat(ex, instanceOf(NotSupportedException.class));
	}

	@Given("^account is closed$")
	public void account_is_closed() throws Throwable {
		bankAccount.close();
	}

	@Given("^account is suspended$")
	public void account_is_suspended() throws Throwable {
		bankAccount.suspend();
	}

	@Then("^it Fails with InvalidAccountStatusException$")
	public void it_Fails_with_InvalidAccountStatusException() throws Throwable {
		assertThat(ex, instanceOf(InvalidAccountStatusException.class));
	}
}