package com.ericsson.inventory.ejbservice.client;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.ericsson.inventory.ejbservice.domain.ClientType;
import com.ericsson.inventory.ejbservice.qualifier.ClientFlag;

@ApplicationScoped
public class ClientServiceFactory {

	@Produces
	@ClientFlag(ClientType.FAKE)
	public ClientHolder getFakeClient() {
		return new FakeClientHolder();
	}

	@Produces
	@ClientFlag(ClientType.LIVE)
	public ClientHolder getLiveClient() {
		return new LiveClientHolder();
	}

}
