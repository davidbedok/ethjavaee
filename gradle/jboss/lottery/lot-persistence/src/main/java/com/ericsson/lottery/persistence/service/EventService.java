package com.ericsson.lottery.persistence.service;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.lottery.persistence.entity.Event;
import com.ericsson.lottery.persistence.exception.PersistenceServiceException;

@Local
public interface EventService {

	void create(String puller, Integer prizePool, int[] numbers) throws PersistenceServiceException;

	Event readLatest() throws PersistenceServiceException;

	List<Event> readAll() throws PersistenceServiceException;

}
