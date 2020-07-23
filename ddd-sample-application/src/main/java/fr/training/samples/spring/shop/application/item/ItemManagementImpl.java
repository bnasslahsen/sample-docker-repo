package fr.training.samples.spring.shop.application.item;

import java.util.List;

import fr.training.samples.spring.shop.domain.item.entity.ItemEntity;
import fr.training.samples.spring.shop.domain.item.repository.ItemRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author bnasslahsen
 *
 */
@Service
@Transactional
public class ItemManagementImpl implements ItemManagement {

	/**
	 * itemRepository of type ItemRepository
	 */
	private final  ItemRepository itemRepository;

	/**
	 * @param itemRepository
	 */
	public ItemManagementImpl(final ItemRepository itemRepository) {
		super();
		this.itemRepository = itemRepository;
	}

	@Override
	@CacheEvict(value = "itemCache", allEntries = true)
	public ItemEntity addItem(final ItemEntity itemEntity) {
		return itemRepository.addItem(itemEntity);
	}

	@Override
	@Cacheable("itemCache")
	public List<ItemEntity> getAllItems() {
		System.out.println("tototot");
		return itemRepository.getAllItems();
	}

}
