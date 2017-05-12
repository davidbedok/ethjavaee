package com.ericsson.lottery.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.lottery.ejbservice.domain.EventStub;
import com.ericsson.lottery.persistence.entity.Event;

@Local
public interface EventConverter {

	List<EventStub> to(List<Event> events);

	EventStub to(Event event);

}
