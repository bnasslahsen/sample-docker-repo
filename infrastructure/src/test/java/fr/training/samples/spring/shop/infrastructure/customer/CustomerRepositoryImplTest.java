package fr.training.samples.spring.shop.infrastructure.customer;

import fr.training.samples.spring.shop.domain.customer.CustomerEntity;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.customer.CustomerVO;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class CustomerRepositoryImplTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	void testCreateAndFind() {
		final CustomerEntity customer = new CustomerEntity(new CustomerVO("nass", "123456"));
		customerRepository.create(customer);
		final CustomerEntity customerCreated = customerRepository.findOne(customer.getId());
		assertNotNull(customerCreated);
		assertEquals("nass", customerCreated.getCustomerVO().getName());
	}

	@Test
	void testFindOne() {
		final CustomerEntity customerEntity = customerRepository.findOne("123e4567-e89b-42d3-a456-556642440000");
		assertNotNull(customerEntity);
		assertEquals("NAME1", customerEntity.getCustomerVO().getName());
	}

	@Test
	void testUpdate() {
		CustomerEntity customerEntity = customerRepository.findOne("123e4567-e89b-42d3-a456-556642440000");
		customerEntity.setCustomerVO(new CustomerVO("nass", "1234567"));
		customerRepository.update(customerEntity);
		assertEquals("1234567", customerEntity.getCustomerVO().getPassword());
	}
}