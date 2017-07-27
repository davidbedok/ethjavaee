package com.ericsson.inventory.ejbservice.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;

import com.ericsson.inventory.ejbservice.client.ClientHolder;
import com.ericsson.inventory.ejbservice.converter.InventoryItemConverter;
import com.ericsson.inventory.ejbservice.cost.CostService;
import com.ericsson.inventory.ejbservice.domain.Client;
import com.ericsson.inventory.ejbservice.domain.ClientType;
import com.ericsson.inventory.ejbservice.domain.InventoryItemStub;
import com.ericsson.inventory.ejbservice.event.NotifierEvent;
import com.ericsson.inventory.ejbservice.exception.AdaptorException;
import com.ericsson.inventory.ejbservice.interceptor.Logged;
import com.ericsson.inventory.ejbservice.interceptor.LoggedInterceptor;
import com.ericsson.inventory.ejbservice.qualifier.ClientFlag;
import com.ericsson.inventory.ejbservice.qualifier.Discount;
import com.ericsson.inventory.ejbservice.qualifier.DiscountQualifier;
import com.ericsson.inventory.ejbservice.qualifier.Random;
import com.ericsson.inventory.ejbservice.service.InventoryConfiguration;
import com.ericsson.inventory.persistence.domain.InventoryItem;
import com.ericsson.inventory.persistence.domain.InventoryType;
import com.ericsson.inventory.persistence.service.InventoryHolder;
import com.ericsson.inventory.persistence.service.InventorySearch;

@Stateless(mappedName = "ejb/inventoryFacade")
public class InventoryFacadeImpl implements InventoryFacade {

	private static final Logger LOGGER = Logger.getLogger(InventoryFacadeImpl.class);

	@Inject
	@Discount
	private CostService costService;

	@Any
	@Inject
	private Instance<CostService> dynamicCostService;

	@Inject
	@ClientFlag(ClientType.LIVE)
	private ClientHolder clientHolder;

	@Inject
	@ClientFlag(ClientType.CUSTOM)
	private ClientHolder customClientHolder;

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

	@Override
	@Interceptors({ LoggedInterceptor.class })
	public void test() {
		LOGGER.info("Test method call");
	}

	@Logged
	@Override
	public InventoryItem getInventory(final String reference) throws AdaptorException {
		final InventoryItem inventory = this.inventoryHolder.get(reference);
		// inventory.setValue(this.costService.getCost(inventory.getValue()));

		CostService dynamicCostService = this.dynamicCostService.select(new AnnotationLiteral<Discount>() {
			private static final long serialVersionUID = 1L;
		}).get();

		CostService dynamicCostService2 = this.dynamicCostService.select(new DiscountQualifier()).get();

		inventory.setValue(dynamicCostService2.getCost(inventory.getValue()));

		final Client client = this.clientHolder.getCurrent();
		this.notifier.fire(new NotifierEvent(client, "Get " + inventory.getName() + " (ref: " + inventory.getReference() + ") inventory item."));
		this.notifier.fire(new NotifierEvent(this.customClientHolder.getCurrent(),
				"Get " + inventory.getName() + " (ref: " + inventory.getReference() + ") inventory item."));
		return inventory;
	}

	@Override
	public InventoryItemStub getInventoryStub(final String reference) throws AdaptorException {
		return this.converter.to(this.inventoryHolder.get(reference));
	}

	@Logged
	@Override
	public List<InventoryItem> getInventories(final InventoryType type) throws AdaptorException {
		final List<InventoryItem> items = this.inventoryHolder.list(type);
		final Client client = this.clientHolder.getCurrent();
		this.notifier.fire(new NotifierEvent(client, "List " + items.size() + " inventory item(s)."));
		return items;
	}

	@Override
	public List<InventoryItem> getInventories(final InventoryType type, final String nameTerm) throws AdaptorException {
		return this.inventorySearch.list(type, nameTerm);
	}

	@Override
	public String getHost() throws AdaptorException {
		return this.configuration.getHost();
	}

	@Logged
	@Override
	public List<Integer> getRandomNumbers(final int quantity) throws AdaptorException {
		final List<Integer> result = new ArrayList<>();
		for (int i = 0; i < quantity; i++) {
			result.add(this.randomNumber.get());
		}
		return result;
	}

}
