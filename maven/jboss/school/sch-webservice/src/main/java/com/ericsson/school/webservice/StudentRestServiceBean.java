package com.ericsson.school.webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.ericsson.school.ejbservice.domain.StudentStub;
import com.ericsson.school.ejbservice.exception.AdaptorException;
import com.ericsson.school.ejbservice.facade.StudentFacade;

@Stateless
public class StudentRestServiceBean implements StudentRestService {

	private static final Logger LOGGER = Logger.getLogger(StudentRestServiceBean.class);

	@EJB
	private StudentFacade facade;

	@Override
	public StudentStub getStudent(final String neptun) throws AdaptorException {
		LOGGER.info("Get Student (" + neptun + ")");
		return this.facade.getStudent(neptun);
	}

	@Override
	public List<StudentStub> getAllStudent() throws AdaptorException {
		LOGGER.info("Get all Students");
		return this.facade.getAllStudents();
	}

	@Override
	public void removeStudent(final String neptun) throws AdaptorException {
		LOGGER.info("Remove Student (" + neptun + ")");
		this.facade.removeStudent(neptun);
	}

	@Override
	public void removeStudentAdvanced(final String neptun) throws AdaptorException {
		LOGGER.info("Remove Student Advanced (" + neptun + ")");
		this.facade.removeStudentAdvanced(neptun);
	}

	@Override
	public Response getStudents(int pageSize, int page) throws AdaptorException {
		List<StudentStub> students = this.facade.getStudents(pageSize, page);
		return Response.status(Status.NOT_FOUND).entity(students).type(MediaType.APPLICATION_JSON).build();
	}

	@Override
	public Response optionsAll(final String path) {
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}
