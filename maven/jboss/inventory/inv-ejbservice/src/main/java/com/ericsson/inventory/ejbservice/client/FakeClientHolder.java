package com.ericsson.inventory.ejbservice.client;

import com.ericsson.inventory.ejbservice.domain.Client;
import com.ericsson.inventory.ejbservice.domain.ClientType;
import com.ericsson.inventory.ejbservice.qualifier.ClientFlag;

@ClientFlag(ClientType.FAKE)
public class FakeClientHolder implements ClientHolder {

	@Override
	public Client getCurrent() {
		return new Client("SVW987", "Scott V. Wright");
	}

}
