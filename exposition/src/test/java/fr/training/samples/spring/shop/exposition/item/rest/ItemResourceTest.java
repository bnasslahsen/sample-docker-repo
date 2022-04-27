
package fr.training.samples.spring.shop.exposition.item.rest;


import java.util.ArrayList;

import fr.training.samples.spring.shop.application.item.ItemManagement;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit Test exemple with mocked server
 */
@WebMvcTest
@AutoConfigureMockMvc
@ComponentScan(basePackages = { "fr.training.samples.spring.shop.common","fr.training.samples.spring.shop.exposition" }, lazyInit = true)
public class ItemResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ItemManagement itemManagement;

	@Test
	public void getAllItems() throws Exception {
		// Pass the mock object to mockito
		Mockito.when(itemManagement.getAllItems()).thenReturn(new ArrayList<>());
		mockMvc.perform(get("/api/items"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));
	}
}
