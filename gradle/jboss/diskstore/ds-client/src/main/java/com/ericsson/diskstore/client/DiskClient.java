package com.ericsson.diskstore.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.ericsson.diskstore.ejbserviceclient.DiskFacadeRemote;
import com.ericsson.diskstore.ejbserviceclient.domain.DiskStub;
import com.ericsson.diskstore.ejbserviceclient.exception.ServiceException;

public class DiskClient {

	private static final Logger LOGGER = Logger.getLogger(DiskClient.class);

	public static void main(final String[] args) throws Exception {
		System.out.println(new DiskClient().invoke("WAM124"));
	}

	private DiskStub invoke(final String reference) {
		DiskStub disk = null;
		try {
			final DiskFacadeRemote facade = this.lookupViaJndiProperties();
			disk = facade.getDisk(reference);
			LOGGER.info(disk);
		} catch (final ServiceException e) {
			e.printStackTrace();
		} catch (final NamingException e) {
			e.printStackTrace();
		}
		return disk;
	}

	private DiskFacadeRemote lookup() throws NamingException {
		final Hashtable<String, String> jndiProperties = new Hashtable<>();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "remote://localhost:4447");
		jndiProperties.put("jboss.naming.client.ejb.context", "true");
		final Context context = new InitialContext(jndiProperties);
		return (DiskFacadeRemote) context.lookup("dsapp/dsservicemodule/DiskStoreService!com.ericsson.diskstore.ejbserviceclient.DiskFacadeRemote");
	}

	private DiskFacadeRemote lookupViaJndiProperties() throws NamingException {
		final Context context = new InitialContext();
		return (DiskFacadeRemote) context.lookup("dsapp/dsservicemodule/DiskStoreService!com.ericsson.diskstore.ejbserviceclient.DiskFacadeRemote");
	}

}