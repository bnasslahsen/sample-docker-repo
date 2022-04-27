package fr.training.samples.spring.shop.infrastructure.item;

import java.util.List;
import java.util.Set;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.item.ItemEntity;
import fr.training.samples.spring.shop.domain.item.ItemRepository;

import org.springframework.stereotype.Repository;

/**
 * @author bnasslahsen
 */
@Repository
public class ItemRepositoryImpl implements ItemRepository {

	private ItemDataJpaRepository itemDataJpaRepository;

	public ItemRepositoryImpl(ItemDataJpaRepository itemDataJpaRepository) {
		this.itemDataJpaRepository = itemDataJpaRepository;
	}

	@Override
	public ItemEntity addItem(ItemEntity itemEntity) {
		return itemDataJpaRepository.save(itemEntity);
	}

	@Override
	public ItemEntity findOne(String itemId) {
		return itemDataJpaRepository.findById(itemId)
				.orElseThrow(() -> new NotFoundException("Item with id:" + itemId + ", not found"));
	}

	@Override
	public List<ItemEntity> getAllItems() {
		return itemDataJpaRepository.findAll();
	}

	@Override
	public Set<ItemEntity> getAllItems(Set<String> itemsId) {
		return itemDataJpaRepository.findByIdIn(itemsId);
	}
}
