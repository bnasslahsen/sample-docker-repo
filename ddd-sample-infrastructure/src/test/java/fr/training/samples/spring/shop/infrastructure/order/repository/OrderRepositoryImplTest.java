package fr.training.samples.spring.shop.infrastructure.order.repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.training.samples.spring.shop.domain.customer.entity.CustomerEntity;
import fr.training.samples.spring.shop.domain.customer.repository.CustomerRepository;
import fr.training.samples.spring.shop.domain.item.entity.ItemEntity;
import fr.training.samples.spring.shop.domain.item.repository.ItemRepository;
import fr.training.samples.spring.shop.domain.order.entity.OrderEntity;
import fr.training.samples.spring.shop.domain.order.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Badr NASS
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class OrderRepositoryImplTest {

    /**
     * orderRepository of type OrderRepository
     */
    @Autowired
    private transient OrderRepository orderRepository;

    /**
     * itemRepository of type ItemRepository
     */
    @Autowired
    private transient ItemRepository itemRepository;

    /**
     * customerRepository of type CustomerRepository
     */
    @Autowired
    private transient CustomerRepository customerRepository;
    

    /**
     * @param customerId
     * @param itemId
     * @param price
     * @return
     */
    private OrderEntity createOrder(final String customerId, final String itemId, final int price) {
        final CustomerEntity customer = customerRepository.findOne(customerId);
        final ItemEntity itemEntity = itemRepository.findOne(itemId);
        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomer(customer);
		orderEntity.setItems(new HashSet<ItemEntity>(Arrays.asList(itemEntity)));
		return orderEntity;
    }

    /**
     * 
     */
    @Test
    public void testAddOrder() {
		final OrderEntity orderEntity = this.createOrder("123e4567-e89b-42d3-a456-556642440000",
				"123e4567-e89b-42d3-a456-556642440001", 99);
        orderRepository.addOrder(orderEntity);
        assertNotNull(orderEntity.getId());
    }

    /**
     * 
     */
    @Test
    public void testAddOrders() {
		final OrderEntity orderEntity1 = this.createOrder("123e4567-e89b-42d3-a456-556642440000",
				"123e4567-e89b-42d3-a456-556642440001", 99);
		final OrderEntity orderEntity2 = this.createOrder("123e4567-e89b-42d3-a456-556642440000",
				"123e4567-e89b-42d3-a456-556642440002", 99);
        final List<OrderEntity> orders = Stream.of(orderEntity1, orderEntity2).collect(Collectors.toList());
        orderRepository.addOrders(orders);
        assertNotNull(orderEntity1.getId());
        assertNotNull(orderEntity2.getId());
    }

    /**
     * 
     */
    @Test
    public void testGetOrdersForCustomer() {
		final Set<OrderEntity> orders = orderRepository.getOrdersForCustomer("123e4567-e89b-42d3-a456-556642440000");
		assertNotNull(orders);
        assertEquals(2, orders.size());
    }

}
