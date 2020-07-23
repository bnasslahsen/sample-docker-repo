package fr.training.samples.spring.shop.domain.item.repository;

import java.util.List;
import java.util.Set;

import fr.training.samples.spring.shop.domain.item.entity.ItemEntity;

/**
 * @author bnasslahsen
 *
 */
public interface ItemRepository {

    /**
     * @param itemEntity
     * @return
     */
    ItemEntity addItem(ItemEntity itemEntity);

    /**
     * @param itemId
     * @return
     */
    ItemEntity findOne(String itemId);

    /**
     * @return
     */
	List<ItemEntity> getAllItems();

    /**
     * @param itemsId
     * @return
     */
	Set<ItemEntity> getAllItems(Set<String> itemsId);

}
