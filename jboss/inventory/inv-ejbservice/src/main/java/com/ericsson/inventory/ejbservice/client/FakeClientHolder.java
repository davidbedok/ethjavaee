package com.ericsson.inventory.ejbservice.client;

import com.ericsson.inventory.ejbservice.domain.Client;

public class FakeClientHolder implements ClientHolder {

	@Override
	public Client getCurrent() {
		return new Client("SVW987", "Scott V. Wright");
	}

}
