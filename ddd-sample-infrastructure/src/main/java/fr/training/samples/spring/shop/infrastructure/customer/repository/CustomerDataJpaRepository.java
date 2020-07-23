package fr.training.samples.spring.shop.infrastructure.customer.repository;

import fr.training.samples.spring.shop.domain.customer.entity.CustomerEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bnasslahsen
 *
 */
public interface CustomerDataJpaRepository extends JpaRepository<CustomerEntity, String> {

}
