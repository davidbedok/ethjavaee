package com.ericsson.inventory.ejbservice.converter;

import javax.annotation.Resource;

import com.ericsson.inventory.ejbservice.domain.InventoryItemStub;
import com.ericsson.inventory.persistence.domain.InventoryItem;

public class InventoryItemConverterImpl implements InventoryItemConverter {

	@Resource(lookup = "java:global/currency")
	private String currency;

	@Resource(lookup = "java:global/exchangeRate")
	private int exchangeRate;

	@Override
	public InventoryItemStub to(InventoryItem item) {
		String label = item.getReference() + "-" + item.getName();
		String price = item.getValue() * this.exchangeRate + " " + this.currency;
		return new InventoryItemStub(label, price);
	}

}
