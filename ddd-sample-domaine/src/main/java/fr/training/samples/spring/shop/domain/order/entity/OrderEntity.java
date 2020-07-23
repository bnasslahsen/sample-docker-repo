package fr.training.samples.spring.shop.domain.order.entity;

import java.util.Set;

import javax.validation.Valid;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;
import fr.training.samples.spring.shop.domain.customer.entity.CustomerEntity;
import fr.training.samples.spring.shop.domain.item.entity.ItemEntity;

/**
 * @author bnasslahsen
 *
 */
public class OrderEntity extends AbstractBaseEntity {

	/**
	 * customer of type CustomerEntity
	 */
	@Valid
	private CustomerEntity customer;

	/**
	 * items of type Set<ItemEntity>
	 */
	private Set<ItemEntity> items;

	public OrderEntity() {
		super();
	}

	/**
	 * All args constructor
	 * 
	 * @param customer
	 * @param items
	 */
	public OrderEntity(final CustomerEntity customer, final Set<ItemEntity> items) {
		super();
		this.customer = customer;
		this.items = items;
	}

	/**
	 * @return
	 */
	public CustomerEntity getCustomer() {
		return customer;
	}

	/**
	 * @return
	 */
	public Set<ItemEntity> getItems() {
		return items;
	}

	/**
	 * @param customer
	 */
	public void setCustomer(final CustomerEntity customer) {
		this.customer = customer;
	}

	/**
	 * @param items
	 */
	public void setItems(final Set<ItemEntity> items) {
		this.items = items;
	}
}
