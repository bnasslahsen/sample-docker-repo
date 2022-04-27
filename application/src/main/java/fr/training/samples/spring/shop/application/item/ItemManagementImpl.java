package fr.training.samples.spring.shop.application.item;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import fr.training.samples.spring.shop.domain.item.ItemEntity;
import fr.training.samples.spring.shop.domain.item.ItemRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author bnasslahsen
 */
@Transactional
@Service
public class ItemManagementImpl implements ItemManagement {

	private ItemRepository itemRepository;

	public ItemManagementImpl(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Override
	@CacheEvict(cacheNames = "itemCache", allEntries = true)
	public ItemEntity addItem(ItemEntity itemEntity) {
		return itemRepository.addItem(itemEntity);
	}

	@Override
	@Cacheable("itemCache")
	public List<ItemEntity> getAllItems() {
		return itemRepository.getAllItems();
	}

	@Override
	public Set<ItemEntity> getAllItems(Set<String> itemsId) {
		return itemRepository.getAllItems(itemsId);
	}
}
