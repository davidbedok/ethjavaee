package com.ericsson.lottery.ejbservice.facade;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.lottery.ejbservice.domain.EventStub;
import com.ericsson.lottery.ejbservice.exception.AdaptorException;

@Local
public interface LotteryFacade {

	List<EventStub> getAllEvents() throws AdaptorException;

	EventStub getLatestEvent() throws AdaptorException;

	void createNewEvent(int[] numbers) throws AdaptorException;

	int checkNumbers(int[] numbers) throws AdaptorException;

}
