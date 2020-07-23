package fr.training.samples.spring.shop.application.customer;

import fr.training.samples.spring.shop.domain.customer.entity.CustomerEntity;
import fr.training.samples.spring.shop.domain.customer.repository.CustomerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author bnasslahsen
 *
 */
@Service
@Transactional
public class CustomerManagementImpl implements CustomerManagement {

	/**
	 * customerRepository of type CustomerRepository
	 */
	private final CustomerRepository customerRepository;

	/**
	 * @param customerRepository
	 */
	public CustomerManagementImpl(final CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public CustomerEntity create(final CustomerEntity customerEntity) {
		return customerRepository.create(customerEntity);
	}

	@Override
	public CustomerEntity findOne(final String customerID) {
		return customerRepository.findOne(customerID);
	}

	@Override
	public CustomerEntity update(CustomerEntity customerEntity) {
		return customerRepository.update(customerEntity);
	}
}
