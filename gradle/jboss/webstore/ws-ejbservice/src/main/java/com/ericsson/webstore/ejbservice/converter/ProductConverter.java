package com.ericsson.webstore.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.webstore.ejbservice.domain.BrandStub;
import com.ericsson.webstore.ejbservice.domain.ProductStub;
import com.ericsson.webstore.persistence.domain.Brand;
import com.ericsson.webstore.persistence.domain.Product;

@Local
public interface ProductConverter {

	Brand to(BrandStub brand);

	BrandStub toStub(Brand brand);

	Product to(ProductStub product);

	ProductStub toStub(Product product);

	List<ProductStub> toStubs(List<Product> products);

	List<Product> to(List<ProductStub> products);

}
