package com.ericsson.diskstore.ejbserviceclient;

import javax.ejb.Remote;

import com.ericsson.diskstore.ejbserviceclient.domain.DiskStub;
import com.ericsson.diskstore.ejbserviceclient.exception.ServiceException;

@Remote
public interface DiskFacadeRemote {

	DiskStub getDisk(String reference) throws ServiceException;

}
