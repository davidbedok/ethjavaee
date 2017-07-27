package com.ericsson.lottery.ejbservice.facade;

import java.util.concurrent.Future;

import javax.ejb.Local;

import com.ericsson.lottery.ejbservice.domain.EventStub;
import com.ericsson.lottery.ejbservice.exception.AdaptorException;

@Local
public interface LiveLotteryFacade {

	Future<EventStub> draw() throws AdaptorException;

}
