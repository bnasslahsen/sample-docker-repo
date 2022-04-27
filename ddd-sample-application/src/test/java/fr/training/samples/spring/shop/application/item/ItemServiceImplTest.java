package fr.training.samples.spring.shop.application.item;

import java.util.Arrays;
import java.util.List;

import fr.training.samples.spring.shop.SpringBootAppBase;
import fr.training.samples.spring.shop.domain.item.ItemVO;
import fr.training.samples.spring.shop.domain.item.entity.ItemEntity;
import fr.training.samples.spring.shop.domain.item.repository.ItemRepository;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author bnasslahsen
 *
 */
public class ItemServiceImplTest  extends SpringBootAppBase {

	/**
	 * itemManagement of type ItemManagement
	 */
	@Autowired
	private ItemManagement itemManagement;

	/**
	 * itemRepository of type ItemRepository
	 */
	@MockBean
	private ItemRepository itemRepository;

	@Test
	public void testGetAllItems() {
		ItemEntity itemEntity = new ItemEntity(new ItemVO("DESC99", 99));
		List<ItemEntity> items = Arrays.asList(itemEntity);
		when(itemRepository.getAllItems()).thenReturn(items);
		List<ItemEntity> itemsResult = itemManagement.getAllItems();
		itemManagement.getAllItems();
		itemManagement.getAllItems();
		itemManagement.getAllItems();
		assertNotNull(itemsResult);
		assertTrue(itemsResult.size() == 1);
	}

	@Test
	public void testAddItem() {
		ItemVO itemVO = new ItemVO("DESC99", 99);
		ItemEntity itemEntity = new ItemEntity(itemVO);
		when(itemRepository.addItem(itemEntity)).thenReturn(itemEntity);

		ItemEntity itemResultEntity = itemManagement.addItem(itemEntity);
		assertNotNull(itemResultEntity);
		assertEquals("DESC99", itemResultEntity.getItemVO().getDescription());
	}
}
