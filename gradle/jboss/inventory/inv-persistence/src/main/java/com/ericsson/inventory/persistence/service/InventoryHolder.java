package com.ericsson.inventory.persistence.service;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.inventory.persistence.domain.InventoryItem;
import com.ericsson.inventory.persistence.domain.InventoryType;

@Local
public interface InventoryHolder {

	InventoryItem get(String reference);

	List<InventoryItem> list(InventoryType type);

	List<InventoryItem> getAll();

}
