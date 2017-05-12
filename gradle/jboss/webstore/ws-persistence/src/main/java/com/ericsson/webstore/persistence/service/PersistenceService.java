package com.ericsson.webstore.persistence.service;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.webstore.persistence.domain.Brand;
import com.ericsson.webstore.persistence.domain.Product;
import com.ericsson.webstore.persistence.exception.PersistenceException;

@Local
public interface PersistenceService {

	void create(Product product) throws PersistenceException;

	Product read(String name) throws PersistenceException;

	List<Product> readAll(String nameTerm) throws PersistenceException;

	List<Product> readAll(Brand brand) throws PersistenceException;

	List<Product> readAll() throws PersistenceException;

	void delete(String name) throws PersistenceException;

}
