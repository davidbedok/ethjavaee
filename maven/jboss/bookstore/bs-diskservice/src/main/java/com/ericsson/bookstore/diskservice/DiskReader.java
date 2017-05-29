package com.ericsson.bookstore.diskservice;

import javax.ejb.Local;

import com.ericsson.bookstore.diskservice.exception.DiskServiceException;
import com.ericsson.diskstore.ejbserviceclient.domain.DiskStub;

@Local
public interface DiskReader {

	DiskStub getDisk(String reference) throws DiskServiceException;

	DiskStub getDiskLocal(String reference) throws DiskServiceException;

}
