package fr.training.samples.spring.shop.application.item;

import java.util.Arrays;
import java.util.List;

import fr.training.samples.spring.shop.domain.item.ItemVO;
import fr.training.samples.spring.shop.domain.item.entity.ItemEntity;
import fr.training.samples.spring.shop.domain.item.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author bnasslahsen
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemServiceImplTest {

	/**
	 * itemManagement of type ItemManagement
	 */
	@Autowired
	private  ItemManagement itemManagement;

	/**
	 * itemRepository of type ItemRepository
	 */
	@MockBean
	private  ItemRepository itemRepository;

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
