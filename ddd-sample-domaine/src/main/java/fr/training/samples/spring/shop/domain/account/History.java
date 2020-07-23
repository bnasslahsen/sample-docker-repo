package fr.training.samples.spring.shop.domain.account;

import java.time.LocalDate;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;
import fr.training.samples.spring.shop.domain.common.vo.Money;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Class of histories (Entity).
 */
public class History extends AbstractBaseEntity {

	private Money balance; // The balance of this history.

	private History previous; // The preceding history.

	private LocalDate date; // The history date.

	/**
	 * Default constructor required by JPA
	 */
	public History() {
		super();
	}

	/**
	 * Constructs a history with the specified balance and preceding history.
	 */
	public History(final Money balance, final History previous, final LocalDate date) {
		this.balance = balance;
		this.previous = previous;
		this.date = date;
	}

	/**
	 * The balance of this history.
	 */
	public Money getBalance() {
		return balance;
	}

	/**
	 * The preceding history.
	 */
	public History getPrevious() {
		return previous;
	}

	/**
	 * The history date.
	 */
	public LocalDate getDate() {
		return date;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(getId()).append(balance).append(previous).toString();
	}
}