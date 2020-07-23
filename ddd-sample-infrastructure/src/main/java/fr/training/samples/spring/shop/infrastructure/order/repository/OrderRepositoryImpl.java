package fr.training.samples.spring.shop.infrastructure.order.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.order.entity.OrderEntity;
import fr.training.samples.spring.shop.domain.order.repository.OrderRepository;

import org.springframework.stereotype.Repository;

/**
 * @author bnasslahsen
 *
 */
@Repository
public class OrderRepositoryImpl implements OrderRepository {

	/**
	 * orderDataJpaRepository of type OrderDataJpaRepository
	 */
	private final OrderDataJpaRepository orderDataJpaRepository;

	/**
	 * The EntityManager
	 */
	private final EntityManager entityManager;

	public OrderRepositoryImpl(OrderDataJpaRepository orderDataJpaRepository, EntityManager entityManager) {
		super();
		this.orderDataJpaRepository = orderDataJpaRepository;
		this.entityManager = entityManager;
	}

	@Override
	public OrderEntity addOrder(final OrderEntity orderEntity) {
		entityManager.persist(orderEntity);
		return orderEntity;
	}

	@Override
	public void addOrders(final List<OrderEntity> orders) {
		orderDataJpaRepository.saveAll(orders);
	}

	@Override
	public OrderEntity findOne(final String orderID) {
		return orderDataJpaRepository.findById(orderID)
				.orElseThrow(() -> new NotFoundException("Order with id:" + orderID + ", not found"));
	}

	@Override
	public Set<OrderEntity> getOrdersForCustomer(final String id) {
		return orderDataJpaRepository.getOrdersForCustomer(id);
	}

	@Override
	public Set<OrderEntity> findAllOrders() {
		List<OrderEntity> lisEntities = orderDataJpaRepository.findAll();
		lisEntities.forEach(p -> p.getItems().size());
		return new HashSet<>(lisEntities);
	}
}
