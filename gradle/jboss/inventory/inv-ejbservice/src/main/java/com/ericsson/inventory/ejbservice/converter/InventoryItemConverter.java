package com.ericsson.inventory.ejbservice.converter;

import com.ericsson.inventory.ejbservice.domain.InventoryItemStub;
import com.ericsson.inventory.persistence.domain.InventoryItem;

public interface InventoryItemConverter {

	InventoryItemStub to(InventoryItem item);

}
