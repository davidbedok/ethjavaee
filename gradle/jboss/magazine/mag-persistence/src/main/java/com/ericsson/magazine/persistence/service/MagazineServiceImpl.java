package com.ericsson.magazine.persistence.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.ericsson.magazine.persistence.entity.Magazine;
import com.ericsson.magazine.persistence.entity.trunk.MagazineCategory;
import com.ericsson.magazine.persistence.exception.PersistenceServiceException;
import com.ericsson.magazine.persistence.parameter.MagazineParameter;
import com.ericsson.magazine.persistence.query.MagazineQuery;

@Stateless(mappedName = "ejb/magazineService")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MagazineServiceImpl implements MagazineService {

	private static final Logger LOGGER = Logger.getLogger(MagazineServiceImpl.class);

	@PersistenceContext(unitName = "mag-persistence-unit")
	private EntityManager entityManager;

	@Override
	public boolean exists(String reference) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Check Magazine by reference (" + reference + ")");
		}
		try {
			return this.entityManager.createNamedQuery(MagazineQuery.COUNT_BY_REFERENCE, Long.class).setParameter(MagazineParameter.REFERENCE, reference)
					.getSingleResult() == 1;
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error during counting Magazines by reference (" + reference + ")! " + e.getLocalizedMessage(), e);
		}
	}

	@Override
	public Magazine create(String reference, String publisher, String title, int numberOfPages, double price, MagazineCategory category)
			throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Create Magazine (reference: " + reference + ", publisher: " + publisher + ", title: " + title + ", numberOfPages: " + numberOfPages
					+ ", price: " + price + ", category: " + category + ")");
		}
		try {
			final Magazine magazine = new Magazine(reference, publisher, title, numberOfPages, price, category);
			this.entityManager.persist(magazine);
			return magazine;
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error during persisting Magazine (" + reference + ")! " + e.getLocalizedMessage(), e);
		}
	}

	@Override
	public Magazine read(Long id) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Magazine by id (" + id + ")");
		}
		Magazine result = null;
		try {
			result = this.entityManager.createNamedQuery(MagazineQuery.GET_BY_ID, Magazine.class).setParameter(MagazineParameter.ID, id).getSingleResult();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Magazine by id (" + id + ")! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public Magazine read(String reference) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Magazine by reference (" + reference + ")");
		}
		Magazine result = null;
		try {
			result = this.entityManager.createNamedQuery(MagazineQuery.GET_BY_REFERENCE, Magazine.class).setParameter(MagazineParameter.REFERENCE, reference)
					.getSingleResult();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Magazine by reference (" + reference + ")! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public List<Magazine> readAll() throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Magazines");
		}
		List<Magazine> result = null;
		try {
			result = this.entityManager.createNamedQuery(MagazineQuery.GET_ALL, Magazine.class).getResultList();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Magazines! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public List<Magazine> read(MagazineCategory category) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Magazines by Category");
		}
		List<Magazine> result = null;
		try {
			result = this.entityManager.createNamedQuery(MagazineQuery.GET_ALL_BY_CATEGORY, Magazine.class).setParameter(MagazineParameter.CATEGORY, category)
					.getResultList();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Magazines! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public Magazine update(String reference, String publisher, String title, int numberOfPages, double price, MagazineCategory category)
			throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Update Magazine (reference: " + reference + ", publisher: " + publisher + ", title: " + title + ", numberOfPages: " + numberOfPages
					+ ", price: " + price + ", category: " + category + ")");
		}
		try {
			final Magazine book = this.read(reference);
			book.setPublisher(publisher);
			book.setTitle(title);
			book.setNumberOfPages(numberOfPages);
			book.setPrice(price);
			book.setCategory(category);
			return this.entityManager.merge(book);
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when merging Magazine! " + e.getLocalizedMessage(), e);
		}
	}

	@Override
	public void delete(String reference) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Remove Magazine by reference (" + reference + ")");
		}
		try {
			this.entityManager.createNamedQuery(MagazineQuery.REMOVE_BY_REFERENCE).setParameter(MagazineParameter.REFERENCE, reference).executeUpdate();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when removing Magazine by reference (" + reference + ")! " + e.getLocalizedMessage(), e);
		}
	}

}