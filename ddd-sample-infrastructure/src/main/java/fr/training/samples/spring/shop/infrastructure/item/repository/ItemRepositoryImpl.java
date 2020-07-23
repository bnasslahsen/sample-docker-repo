package fr.training.samples.spring.shop.infrastructure.item.repository;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.item.entity.ItemEntity;
import fr.training.samples.spring.shop.domain.item.repository.ItemRepository;

import org.springframework.stereotype.Repository;

/**
 * @author bnasslahsen
 *
 */
@Repository
public class ItemRepositoryImpl implements ItemRepository {

	/**
	 * itemDataJpaRepository of type ItemDataJpaRepository
	 */
	private final transient ItemDataJpaRepository itemDataJpaRepository;

	/**
	 * the EntityManager
	 */
	private final transient EntityManager entityManager;


  public ItemRepositoryImpl(ItemDataJpaRepository itemDataJpaRepository, EntityManager entityManager) {
		super();
		this.itemDataJpaRepository = itemDataJpaRepository;
		this.entityManager = entityManager;
	}

	@Override
	public ItemEntity addItem(final ItemEntity item) {
		entityManager.persist(item);
		return item;
	}


	@Override
	public ItemEntity findOne(final String itemId) {
		return itemDataJpaRepository.findById(itemId)
				.orElseThrow(() -> new NotFoundException("Item with id:" + itemId + ", not found"));
	}

	@Override
	public List<ItemEntity> getAllItems() {
		return itemDataJpaRepository.findAll();
	}

	@Override
	public Set<ItemEntity> getAllItems(final Set<String> itemsId) {
		return itemDataJpaRepository.findByIdIn(itemsId);
	}

}
