package fr.training.samples.spring.shop.application.order;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.training.samples.spring.shop.SpringBootAppTest;
import fr.training.samples.spring.shop.domain.customer.CustomerEntity;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.customer.CustomerVO;
import fr.training.samples.spring.shop.domain.item.ItemEntity;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import fr.training.samples.spring.shop.domain.item.ItemVO;
import fr.training.samples.spring.shop.domain.order.OrderEntity;
import fr.training.samples.spring.shop.domain.order.OrderRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class OrderManagementImplTest extends SpringBootAppTest {

	@Autowired
	private OrderManagement orderManagement;

	@MockBean
	private OrderRepository orderRepository;

	@MockBean
	private CustomerRepository customerRepository;

	@MockBean
	private ItemRepository itemRepository;

	@Test
	public void addOrder() {
		final OrderEntity orderEntity = createOrder();
		when(orderRepository.addOrder(orderEntity)).thenReturn(orderEntity);
		final OrderEntity orderResult = orderManagement.addOrder(orderEntity);
		assertNotNull(orderResult);
	}

	@Test
	public void getOrdersForCustomer() {
		final OrderEntity orderEntity1 = createOrder();
		final OrderEntity orderEntity2 = createOrder();
		final Set<OrderEntity> orders = Stream.of(orderEntity1, orderEntity2).collect(Collectors.toSet());
		when(orderRepository.getOrdersForCustomer("customerId")).thenReturn(orders);
		final Set<OrderEntity> ordersResult = orderManagement.getOrdersForCustomer("customerId");
		assertEquals(2, ordersResult.size());
	}

	@Test
	public void findOne() {
		final OrderEntity orderEntity = createOrder();
		orderEntity.setId("testId");
		when(orderRepository.findOne("testId")).thenReturn(orderEntity);
		OrderEntity orderEntityResult = orderManagement.findOne("testId");
		assertEquals("nass", orderEntityResult.getCustomer().getCustomerVO().getName());
	}

	@Test
	void testAddOrders() {
		final OrderEntity orderEntity1 = this.createOrder("123e4567-e89b-42d3-a456-556642440000",
				"123e4567-e89b-42d3-a456-556642440001", 99);
		final OrderEntity orderEntity2 = this.createOrder("123e4567-e89b-42d3-a456-556642440000",
				"123e4567-e89b-42d3-a456-556642440002", 99);
		final List<OrderEntity> orders = Stream.of(orderEntity1, orderEntity2).collect(Collectors.toList());
		orderRepository.addOrders(orders);
		assertNotNull(orderEntity1.getId());
		assertNotNull(orderEntity2.getId());
	}

	private OrderEntity createOrder(final String customerId, final String itemId, final int price) {
		final CustomerEntity customer = customerRepository.findOne(customerId);
		final ItemEntity itemEntity = itemRepository.findOne(itemId);
		final OrderEntity orderEntity = new OrderEntity();
		orderEntity.setCustomer(customer);
		orderEntity.setItems(new HashSet<ItemEntity>(Arrays.asList(itemEntity)));
		return orderEntity;
	}

	private OrderEntity createOrder() {
		final CustomerEntity customer = new CustomerEntity(new CustomerVO("nass", "123456"));
		final ItemEntity itemEntity = new ItemEntity(new ItemVO("DESC99", 99));
		final OrderEntity orderEntity = new OrderEntity();
		orderEntity.setCustomer(customer);
		orderEntity.setItems(Stream.of(itemEntity).collect(Collectors.toSet()));
		return orderEntity;
	}
}