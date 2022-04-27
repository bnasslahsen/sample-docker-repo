package fr.training.samples.spring.shop.application.order;

import java.util.List;
import java.util.Set;

import fr.training.samples.spring.shop.domain.order.OrderEntity;
import fr.training.samples.spring.shop.domain.order.OrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderManagementImpl implements OrderManagement{

	private OrderRepository orderRepository;

	public OrderManagementImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public OrderEntity addOrder(OrderEntity order) {
		return orderRepository.addOrder(order);
	}

	@Override
	public OrderEntity findOne(String orderID) {
		return orderRepository.findOne(orderID);
	}

	@Override
	public Set<OrderEntity> getOrdersForCustomer(String customerID) {
		return orderRepository.getOrdersForCustomer(customerID);
	}

	@Override
	public void addOrders(List<OrderEntity> orders) {
		orderRepository.addOrders(orders);
	}
}
