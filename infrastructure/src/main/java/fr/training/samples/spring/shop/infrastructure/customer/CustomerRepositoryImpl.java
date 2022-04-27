package fr.training.samples.spring.shop.infrastructure.customer;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.customer.CustomerEntity;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;

import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	private final CustomerDataJpaRepository customerDataJpaRepository;

	public CustomerRepositoryImpl(CustomerDataJpaRepository customerDataJpaRepository) {
		super();
		this.customerDataJpaRepository = customerDataJpaRepository;
	}

	@Override
	public CustomerEntity create(final CustomerEntity customer) {
		return customerDataJpaRepository.save(customer);
	}

	@Override
	public CustomerEntity findOne(final String customerID) {
		return customerDataJpaRepository.findById(customerID)
				.orElseThrow(() -> new NotFoundException("Customer with id:" + customerID + ", not found"));
	}

	@Override
	public CustomerEntity update(CustomerEntity customerEntity) {
		return customerDataJpaRepository.save(customerEntity);
	}
}
