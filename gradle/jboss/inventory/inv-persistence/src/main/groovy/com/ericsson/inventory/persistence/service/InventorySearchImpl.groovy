package com.ericsson.inventory.persistence.service

import com.ericsson.inventory.persistence.domain.InventoryItem
import com.ericsson.inventory.persistence.domain.InventoryType

import javax.ejb.EJB
import javax.ejb.Stateless

@Stateless
class InventorySearchImpl implements InventorySearch {

	@EJB
	private InventoryHolder holder

	@Override
	List<InventoryItem> list(InventoryType type, String nameTerm) {
		holder.getAll().findAll { it -> it.type == type && it.name.startsWith(nameTerm) }
	}
}
