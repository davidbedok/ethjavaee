package com.ericsson.bookstore.diskservice;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.ericsson.bookstore.diskservice.exception.DiskServiceException;
import com.ericsson.diskstore.ejbservice.facade.DiskFacade;
import com.ericsson.diskstore.ejbserviceclient.DiskFacadeRemote;
import com.ericsson.diskstore.ejbserviceclient.domain.DiskStub;
import com.ericsson.diskstore.ejbserviceclient.exception.ServiceException;

@Stateless
public class DiskReaderImpl implements DiskReader {

	private static final Logger LOGGER = Logger.getLogger(DiskReaderImpl.class);

	@Override
	public DiskStub getDisk(String reference) throws DiskServiceException {
		LOGGER.debug("Get Disk by reference (" + reference + ")");
		DiskStub result = null;
		try {
			DiskFacadeRemote remote = this.getDiskFacadeRemote();
			result = remote.getDisk(reference);
		} catch (ServiceException | NamingException e) {
			LOGGER.error(e, e);
			throw new DiskServiceException(e.getLocalizedMessage());
		}
		return result;
	}

	private DiskFacadeRemote getDiskFacadeRemote() throws NamingException {
		DiskFacadeRemote result = null;
		Context context = new InitialContext();
		result = (DiskFacadeRemote) context
				.lookup("java:jboss/exported/diskstore-application/dsservicemodule/DiskStoreService!com.ericsson.diskstore.ejbserviceclient.DiskFacadeRemote");
		return result;
	}

	@Override
	public DiskStub getDiskLocal(String reference) throws DiskServiceException {
		LOGGER.debug("Get Disk by reference (" + reference + ")");
		DiskStub result = null;
		try {
			DiskFacade local = this.getDiskFacadeLocal();
			result = local.getDisk(reference);
		} catch (ServiceException | NamingException e) {
			LOGGER.error(e, e);
			throw new DiskServiceException(e.getLocalizedMessage());
		}
		return result;
	}

	private DiskFacade getDiskFacadeLocal() throws NamingException {
		DiskFacade result = null;
		Context context = new InitialContext();
		result = (DiskFacade) context
				.lookup("java:global/diskstore-application/dsservicemodule/DiskStoreService!com.ericsson.diskstore.ejbservice.facade.DiskFacade");
		return result;
	}

}
