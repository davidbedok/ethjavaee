package com.ericsson.inventory.ejbservice.facade;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.inventory.ejbservice.domain.InventoryItemStub;
import com.ericsson.inventory.ejbservice.exception.AdaptorException;
import com.ericsson.inventory.persistence.domain.InventoryItem;
import com.ericsson.inventory.persistence.domain.InventoryType;

@Local
public interface InventoryFacade {

	InventoryItem getInventory(String reference) throws AdaptorException;

	List<InventoryItem> getInventories(InventoryType type) throws AdaptorException;

	List<InventoryItem> getInventories(InventoryType type, String nameTerm) throws AdaptorException;

	String getHost() throws AdaptorException;

	InventoryItemStub getInventoryStub(String reference) throws AdaptorException;

	List<Integer> getRandomNumbers(int quantity) throws AdaptorException;

}
