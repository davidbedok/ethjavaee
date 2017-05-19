package com.ericsson.webstore.ejbservice.service;

import javax.ejb.Local;

import com.ericsson.webstore.ejbservice.domain.Basket;
import com.ericsson.webstore.ejbservice.exception.ServiceException;

@Local
public interface WebBasketService {

	void setIdentifier(String identifier) throws ServiceException;

	String getIdentifier() throws ServiceException;

	int getBasketSize() throws ServiceException;

	void addItem(String productName) throws ServiceException;

	void removeItem(String productName) throws ServiceException;

	Basket getContent() throws ServiceException;

}
