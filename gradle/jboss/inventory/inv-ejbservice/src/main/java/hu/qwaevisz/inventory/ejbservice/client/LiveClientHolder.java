package hu.qwaevisz.inventory.ejbservice.client;

import hu.qwaevisz.inventory.ejbservice.domain.ClientType;
import hu.qwaevisz.inventory.ejbservice.qualifier.ClientFlag;

@ClientFlag(ClientType.LIVE)
public class LiveClientHolder extends AbstractClientHolder {

	public LiveClientHolder() {
		super("MCF012", "Matthew C. Flores");
	}

}
