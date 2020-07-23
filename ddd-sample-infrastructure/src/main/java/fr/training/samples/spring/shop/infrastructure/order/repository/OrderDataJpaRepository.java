package fr.training.samples.spring.shop.infrastructure.order.repository;

import java.util.Set;

import fr.training.samples.spring.shop.domain.order.entity.OrderEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author bnasslahsen
 *
 */
public interface OrderDataJpaRepository extends JpaRepository<OrderEntity, String> {

	@Query("select o from OrderEntity o join fetch o.items where o.customer.id = :customerId")
	Set<OrderEntity> getOrdersForCustomer(@Param("customerId") String customerId);
}
