package fr.training.samples.spring.shop.domain.order.repository;

import java.util.List;
import java.util.Set;

import fr.training.samples.spring.shop.domain.order.entity.OrderEntity;

/**
 * @author Badr NASS
 *
 */
public interface OrderRepository {

    /**
     * @param order
     * @return
     */
    OrderEntity addOrder(OrderEntity order);

    /**
     * @param orders
     */
    void addOrders(List<OrderEntity> orders);

    /**
     * @param orderID
     * @return
     */
    OrderEntity findOne(String orderID);

    /**
     * @param customerID
     * @return
     */
	Set<OrderEntity> getOrdersForCustomer(String customerID);

	Set<OrderEntity> findAllOrders();

}
