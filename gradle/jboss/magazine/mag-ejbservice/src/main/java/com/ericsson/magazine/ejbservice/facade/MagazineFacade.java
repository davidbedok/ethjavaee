package com.ericsson.magazine.ejbservice.facade;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.magazine.ejbservice.domain.MagazineCategoryStub;
import com.ericsson.magazine.ejbservice.domain.MagazineCriteria;
import com.ericsson.magazine.ejbservice.domain.MagazineStub;
import com.ericsson.magazine.ejbservice.exception.FacadeException;

@Local
public interface MagazineFacade {

	MagazineStub getReference(String reference) throws FacadeException;

	List<MagazineStub> getReferences(MagazineCriteria criteria) throws FacadeException;

	MagazineStub saveMagazine(String reference, String publisher, String title, int numberOfPages, double price, MagazineCategoryStub category)
			throws FacadeException;

	void removeMagazine(String reference) throws FacadeException;

}
