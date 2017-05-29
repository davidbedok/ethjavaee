package com.ericsson.school.webservice;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ericsson.school.ejbservice.domain.StudentStub;
import com.ericsson.school.ejbservice.exception.AdaptorException;

@Path("/student")
public interface StudentRestService {

	@GET
	@Path("/{neptun}")
	@Produces("application/json")
	StudentStub getStudent(@PathParam("neptun") String neptun) throws AdaptorException;

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	List<StudentStub> getAllStudent() throws AdaptorException;

	@GET
	@Path("/list/{page}")
	@Produces(MediaType.APPLICATION_JSON)
	Response getStudents(@DefaultValue("3") @QueryParam("pagesize") int pageSize, @PathParam("page") int page) throws AdaptorException;

	@DELETE
	@Path("/{neptun}")
	void removeStudent(@PathParam("neptun") String neptun) throws AdaptorException;

	@DELETE
	@Path("/advanced/{neptun}")
	void removeStudentAdvanced(@PathParam("neptun") String neptun) throws AdaptorException;

	@OPTIONS
	@Path("{path:.*}")
	Response optionsAll(@PathParam("path") String path);

}
