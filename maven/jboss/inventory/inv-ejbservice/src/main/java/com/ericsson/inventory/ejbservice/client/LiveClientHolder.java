package com.ericsson.inventory.ejbservice.client;

import com.ericsson.inventory.ejbservice.domain.Client;
import com.ericsson.inventory.ejbservice.domain.ClientType;
import com.ericsson.inventory.ejbservice.qualifier.ClientFlag;

@ClientFlag(ClientType.LIVE)
public class LiveClientHolder implements ClientHolder {

	@Override
	public Client getCurrent() {
		return new Client("MCF012", "Matthew C. Flores");
	}

}
