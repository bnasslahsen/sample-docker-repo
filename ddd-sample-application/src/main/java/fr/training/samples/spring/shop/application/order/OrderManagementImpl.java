package fr.training.samples.spring.shop.application.order;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.training.samples.spring.shop.domain.common.exception.BusinessException;
import fr.training.samples.spring.shop.domain.customer.entity.CustomerEntity;
import fr.training.samples.spring.shop.domain.customer.repository.CustomerRepository;
import fr.training.samples.spring.shop.domain.item.entity.ItemEntity;
import fr.training.samples.spring.shop.domain.item.repository.ItemRepository;
import fr.training.samples.spring.shop.domain.order.entity.OrderEntity;
import fr.training.samples.spring.shop.domain.order.repository.OrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @author bnasslahsen
 *
 */
@Service
@Transactional
public class OrderManagementImpl implements OrderManagement {

	/**
	 * orderRepository of type OrderRepository
	 */
	private final OrderRepository orderRepository;

	/**
	 * customerRepository of type CustomerRepository
	 */
	private final CustomerRepository customerRepository;

	/**
	 * itemRepository of type ItemRepository
	 */
	private final ItemRepository itemRepository;

	/**
	 * @param orderRepository
	 * @param customerRepository
	 * @param itemRepository
	 */
	public OrderManagementImpl(final OrderRepository orderRepository, final CustomerRepository customerRepository,
			final ItemRepository itemRepository) {

		super();
		this.orderRepository = orderRepository;
		this.customerRepository = customerRepository;
		this.itemRepository = itemRepository;
	}


	@Override
	public OrderEntity addOrder(final OrderEntity orderEntity) {
		final CustomerEntity customerEntity = customerRepository.findOne(orderEntity.getCustomer().getId());
		final Set<ItemEntity> items = itemRepository
				.getAllItems(orderEntity.getItems().stream().map(ItemEntity::getId).collect(Collectors.toSet()));
		if (CollectionUtils.isEmpty(items))
			throw new BusinessException("The order should contain at least one existing item");
		orderEntity.setCustomer(customerEntity);
		orderEntity.setItems(items);
		return orderRepository.addOrder(orderEntity);
	}

	@Override
	public OrderEntity findOne(final String orderID) {
		return orderRepository.findOne(orderID);
	}

	@Override
	public Set<OrderEntity> getOrdersForCustomer(final String customerID) {
		return orderRepository.getOrdersForCustomer(customerID);
	}

	@Override
	public void addOrders(List<OrderEntity> orders) {
		orderRepository.addOrders(orders);
	}

	@Override
	public Set<OrderEntity> findAllOrders() {
		return orderRepository.findAllOrders();
	}
}
