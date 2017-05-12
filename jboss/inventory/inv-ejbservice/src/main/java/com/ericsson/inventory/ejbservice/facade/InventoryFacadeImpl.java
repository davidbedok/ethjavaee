package com.ericsson.inventory.ejbservice.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.ericsson.inventory.ejbservice.client.ClientHolder;
import com.ericsson.inventory.ejbservice.converter.InventoryItemConverter;
import com.ericsson.inventory.ejbservice.cost.CostService;
import com.ericsson.inventory.ejbservice.domain.Client;
import com.ericsson.inventory.ejbservice.domain.ClientType;
import com.ericsson.inventory.ejbservice.domain.InventoryItemStub;
import com.ericsson.inventory.ejbservice.event.NotifierEvent;
import com.ericsson.inventory.ejbservice.exception.AdaptorException;
import com.ericsson.inventory.ejbservice.interceptor.Logged;
import com.ericsson.inventory.ejbservice.qualifier.ClientFlag;
import com.ericsson.inventory.ejbservice.qualifier.Discount;
import com.ericsson.inventory.ejbservice.qualifier.Random;
import com.ericsson.inventory.ejbservice.service.InventoryConfiguration;
import com.ericsson.inventory.persistence.domain.InventoryItem;
import com.ericsson.inventory.persistence.domain.InventoryType;
import com.ericsson.inventory.persistence.service.InventoryHolder;
import com.ericsson.inventory.persistence.service.InventorySearch;

@Stateless(mappedName = "ejb/inventoryFacade")
public class InventoryFacadeImpl implements InventoryFacade {

	@Inject
	@Discount
	private CostService costService;

	@Inject
	@ClientFlag(ClientType.LIVE)
	private ClientHolder clientHolder;

	@Inject
	private InventoryConfiguration configuration;

	@Inject
	@Random
	private Instance<Integer> randomNumber;

	@Inject
	private Event<NotifierEvent> notifier;

	@EJB
	private InventoryHolder inventoryHolder;

	@EJB
	private InventorySearch inventorySearch;

	@Inject
	private InventoryItemConverter converter;

	@Logged
	@Override
	public InventoryItem getInventory(String reference) throws AdaptorException {
		final InventoryItem inventory = this.inventoryHolder.get(reference);
		inventory.setValue(this.costService.getCost(inventory.getValue()));
		final Client client = this.clientHolder.getCurrent();
		this.notifier.fire(new NotifierEvent(client, "Get " + inventory.getName() + " (ref: " + inventory.getReference() + ") inventory item."));
		return inventory;
	}

	@Override
	public InventoryItemStub getInventoryStub(String reference) throws AdaptorException {
		return this.converter.to(this.inventoryHolder.get(reference));
	}

	@Logged
	@Override
	public List<InventoryItem> getInventories(InventoryType type) throws AdaptorException {
		final List<InventoryItem> items = this.inventoryHolder.list(type);
		final Client client = this.clientHolder.getCurrent();
		this.notifier.fire(new NotifierEvent(client, "List " + items.size() + " inventory item(s)."));
		return items;
	}

	@Override
	public List<InventoryItem> getInventories(InventoryType type, String nameTerm) throws AdaptorException {
		return this.inventorySearch.list(type, nameTerm);
	}

	@Override
	public String getHost() throws AdaptorException {
		return this.configuration.getHost();
	}

	@Logged
	@Override
	public List<Integer> getRandomNumbers(int quantity) throws AdaptorException {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < quantity; i++) {
			result.add(this.randomNumber.get());
		}
		return result;
	}

}
