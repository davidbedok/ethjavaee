package com.ericsson.webstore.ejbservice.service;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.webstore.common.doclet.Description;
import com.ericsson.webstore.ejbservice.domain.BrandStub;
import com.ericsson.webstore.ejbservice.domain.ProductStub;
import com.ericsson.webstore.ejbservice.exception.ServiceException;

@Local
@Description("This Store service is good for getting some information from the catalog.")
public interface StoreService {

	@Description("Use that method to add a new Product to the catalog.")
	void add(@Description("The brand new Product") ProductStub product) throws ServiceException;

	@Description("Use that method to find a Product by name.")
	ProductStub get(@Description("Name of the Product") String name) throws ServiceException;

	@Description("Use that method to list Products which names contain the given term.")
	List<ProductStub> list(@Description("Name term of the Product") String nameTerm) throws ServiceException;

	@Description("List all products which are produced by the given brand.")
	List<ProductStub> list(@Description("Brand of the products") BrandStub brand) throws ServiceException;

	@Description("List all products.")
	List<ProductStub> getAll() throws ServiceException;

	@Description("Delete a product from the catalog.")
	void remove(@Description("Name of the Product") String name) throws ServiceException;

}
