package com.ericsson.school.restclient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.client.ClientResponse;

import com.ericsson.school.restclient.domain.MarkCriteria;
import com.ericsson.school.restclient.domain.MarkStub;

@Path("/mark")
public interface MarkRestService {

	@POST
	@Consumes("application/xml")
	@Produces("application/xml")
	@Path("/get/{neptun}")
	ClientResponse<MarkStub> getMarks(@PathParam("neptun") String studentNeptun, MarkCriteria criteria);

}
