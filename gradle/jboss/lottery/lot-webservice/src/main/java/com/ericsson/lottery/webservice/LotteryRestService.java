package com.ericsson.lottery.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.ericsson.lottery.ejbservice.domain.EventStub;
import com.ericsson.lottery.ejbservice.exception.AdaptorException;

@Path("/service")
public interface LotteryRestService {

	@GET
	@Path("/event/list")
	@Produces("application/json")
	List<EventStub> getAllEvents() throws AdaptorException;

	@GET
	@Path("/event/latest")
	@Produces("application/json")
	EventStub getLatestEvent() throws AdaptorException;

	@POST
	@Path("/check")
	int checkNumbers(int[] numbers) throws AdaptorException;

	@OPTIONS
	@Path("{path:.*}")
	Response optionsAll(@PathParam("path") String path);

}
