package com.ericsson.webstore.ejbservice.service;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.webstore.ejbservice.domain.BrandStub;
import com.ericsson.webstore.ejbservice.domain.ProductStub;
import com.ericsson.webstore.ejbservice.exception.ServiceException;

@Local
public interface StoreService {

	void add(ProductStub product) throws ServiceException;

	ProductStub get(String name) throws ServiceException;

	List<ProductStub> list(String nameTerm) throws ServiceException;

	List<ProductStub> list(BrandStub brand) throws ServiceException;

	List<ProductStub> getAll() throws ServiceException;

	void remove(String name) throws ServiceException;

}
