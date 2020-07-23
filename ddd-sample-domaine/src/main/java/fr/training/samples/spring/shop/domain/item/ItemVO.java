package fr.training.samples.spring.shop.domain.item;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * @author bnasslahsen
 *
 */
public class ItemVO implements Serializable {

	/**
	 * serialVersionUID of type long
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * description of type String
	 */
	private String description;

	/**
	 * price of type int
	 */
	private int price;

	/**
	 *
	 */
	public ItemVO() {
		super();
	}

	/**
	 * @param description
	 * @param price
	 */
	public ItemVO(@NotNull final String description, final int price) {
		this.description = description;
		this.price = price;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(@NotNull final String description) {
		this.description = description;
	}

	/**
	 * @return
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price
	 */
	public void setPrice(final int price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ItemVO itemVO = (ItemVO) o;

		if (price != itemVO.price) return false;
		return description != null ? description.equals(itemVO.description) : itemVO.description == null;
	}

	@Override
	public int hashCode() {
		int result = description != null ? description.hashCode() : 0;
		result = 31 * result + price;
		return result;
	}
}
