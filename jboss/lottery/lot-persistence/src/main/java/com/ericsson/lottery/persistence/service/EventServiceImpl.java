package com.ericsson.lottery.persistence.service;

import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.ericsson.lottery.persistence.entity.Event;
import com.ericsson.lottery.persistence.exception.PersistenceServiceException;
import com.ericsson.lottery.persistence.query.EventQuery;

@Stateless(mappedName = "ejb/eventService")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class EventServiceImpl implements EventService {

	private static final Logger LOGGER = Logger.getLogger(EventServiceImpl.class);

	@PersistenceContext(unitName = "lot-persistence-unit")
	private EntityManager entityManager;

	@Override
	public void create(String puller, Integer prizePool, int[] numbers) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Create a new Event (puller: " + puller + ", prizePool: " + prizePool + ", numbers: " + Arrays.toString(numbers) + ")");
		}
		try {
			final Event event = new Event(puller, prizePool);
			for (final int number : numbers) {
				event.addNumber(number);
			}
			this.entityManager.persist(event);
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Events! " + e.getLocalizedMessage(), e);
		}
	}

	@Override
	public Event readLatest() throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Read latest Event");
		}
		Event result = null;
		try {
			result = this.entityManager.createNamedQuery(EventQuery.GET_LATEST, Event.class).setMaxResults(1).getSingleResult();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching latest Event! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public List<Event> readAll() throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Read all Events");
		}
		List<Event> result = null;
		try {
			result = this.entityManager.createNamedQuery(EventQuery.GET_ALL, Event.class).getResultList();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Events! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

}
