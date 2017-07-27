package com.ericsson.lottery.ejbservice.facade;

import java.util.Random;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.ericsson.lottery.ejbservice.domain.EventStub;
import com.ericsson.lottery.ejbservice.exception.AdaptorException;
import com.ericsson.lottery.ejbservice.holder.LotteryStateHolder;
import com.ericsson.lottery.persistence.exception.PersistenceServiceException;
import com.ericsson.lottery.persistence.service.EventService;

@Stateless(mappedName = "ejb/liveLotteryFacade")
public class LiveLotteryFacadeImpl implements LiveLotteryFacade {

	private static final Logger LOGGER = Logger.getLogger(LiveLotteryFacadeImpl.class);

	@EJB
	private EventService eventService;

	@EJB
	private LotteryFacade lotteryFacade;

	@EJB
	private LotteryStateHolder stateHolder;

	@Override
	@Asynchronous
	public Future<EventStub> draw() throws AdaptorException {
		Random random = new Random();

		int[] numbers = new int[5];
		for (int i = 0; i < numbers.length; i++) {
			// FIXME possible duplicates
			numbers[i] = random.nextInt(90) + 1;
			try {
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				LOGGER.error(e, e);
				throw new AdaptorException(e.getLocalizedMessage());
			}
		}

		try {
			this.eventService.create(this.stateHolder.getCurrentPuller(), this.stateHolder.getCurrentPrizePool(), numbers);
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new AdaptorException(e.getLocalizedMessage());
		}
		return new AsyncResult<EventStub>(this.lotteryFacade.getLatestEvent());
	}

}
