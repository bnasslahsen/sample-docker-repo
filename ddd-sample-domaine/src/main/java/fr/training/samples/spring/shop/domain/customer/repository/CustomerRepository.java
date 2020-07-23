package fr.training.samples.spring.shop.domain.customer.repository;

import fr.training.samples.spring.shop.domain.customer.entity.CustomerEntity;

/**
 * @author bnasslahsen
 *
 */
public interface CustomerRepository {

	/**
	 * @param customer
	 *            the customer to create
	 * @return the Customer Entity
	 */
	CustomerEntity create(CustomerEntity customer);

	/**
	 * @param id
	 *            the Customer id
	 * @return the customer if found
	 */
	CustomerEntity findOne(String id);

	/**
	 * @param customerEntity
	 *            the customer to update
	 * @return the Customer Entity
	 */
	CustomerEntity update(CustomerEntity customerEntity);

}
