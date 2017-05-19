package com.ericsson.webstore.persistence.service;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.webstore.persistence.domain.Brand;
import com.ericsson.webstore.persistence.domain.Product;

@Local
public interface StoreHolder {

	void create(Product product);

	Product read(String name);

	List<Product> readAll(String nameTerm);

	List<Product> readAll(Brand brand);

	List<Product> readAll();

	void delete(String name);

}
