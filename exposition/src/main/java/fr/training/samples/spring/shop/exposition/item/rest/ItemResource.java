package fr.training.samples.spring.shop.exposition.item.rest;

import java.net.URI;
import java.util.List;

import fr.training.samples.spring.shop.application.item.ItemManagement;
import fr.training.samples.spring.shop.domain.item.ItemEntity;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class ItemResource {

	private static final Logger LOG = LoggerFactory.getLogger(ItemResource.class);

	private final ItemMapper itemMapper;

	private ItemManagement itemManagement;

	public ItemResource(ItemManagement itemManagement, ItemMapper itemMapper) {
		this.itemManagement = itemManagement;
		this.itemMapper = itemMapper;
	}

	@GetMapping("/items")
	@Timed("itemResource.showItems")
	public List<ItemDTO> showItems() {
		final List<ItemEntity> itemEntities = itemManagement.getAllItems();
		LOG.info("Number of items returned: {}", itemEntities.size());
		return itemMapper.mapToDtoList(itemEntities);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/items")
	@Timed("itemResource.addItem")
	public ResponseEntity<URI> addItem(@RequestBody final ItemLightDTO itemDTO) {
		final ItemEntity itemEntity = itemMapper.mapToEntity(itemDTO);
		itemManagement.addItem(itemEntity);
		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(itemEntity.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}