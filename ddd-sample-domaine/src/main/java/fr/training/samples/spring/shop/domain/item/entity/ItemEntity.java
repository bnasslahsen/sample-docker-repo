package fr.training.samples.spring.shop.domain.item.entity;

import java.util.Set;

import javax.validation.Valid;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;
import fr.training.samples.spring.shop.domain.item.ItemVO;
import fr.training.samples.spring.shop.domain.order.entity.OrderEntity;

/**
 * @author bnasslahsen
 *
 */
public class ItemEntity extends AbstractBaseEntity {

	/**
	 * itemVO of type ItemVO
	 */
	@Valid
	private ItemVO itemVO;

	/**
	 * orders of type Set<OrderEntity>
	 */
	private Set<OrderEntity> orders;

	/**
	 *
	 */
	public ItemEntity() {
		super();
	}

	/**
	 * @param itemVO
	 */
	public ItemEntity(final ItemVO itemVO) {
		this.itemVO = itemVO;
	}


	public ItemEntity(String id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public ItemVO getItemVO() {
		return itemVO;
	}

	/**
	 * @param itemVO
	 */
	public void setItemVO(final ItemVO itemVO) {
		this.itemVO = itemVO;
	}

	/**
	 * @return
	 */
	public Set<OrderEntity> getOrders() {
		return orders;
	}

	/**
	 * @param orders
	 */
	public void setOrders(final Set<OrderEntity> orders) {
		this.orders = orders;
	}
}
