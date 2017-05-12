package com.ericsson.school.ejbservice.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.ericsson.school.ejbservice.converter.StudentConverter;
import com.ericsson.school.ejbservice.domain.StudentStub;
import com.ericsson.school.ejbservice.exception.AdaptorException;
import com.ericsson.school.ejbservice.util.ApplicationError;
import com.ericsson.school.persistence.exception.AdvancedPersistenceServiceException;
import com.ericsson.school.persistence.exception.PersistenceServiceException;
import com.ericsson.school.persistence.service.MarkService;
import com.ericsson.school.persistence.service.StudentService;

@Stateless(mappedName = "ejb/studentFacade")
public class StudentFacadeImpl implements StudentFacade {

	private static final Logger LOGGER = Logger.getLogger(StudentFacadeImpl.class);

	@EJB
	private StudentService studentService;

	@EJB
	private MarkService markService;

	@EJB
	private StudentConverter converter;

	@Override
	public StudentStub getStudent(final String neptun) throws AdaptorException {
		try {
			final StudentStub stub = this.converter.to(this.studentService.read(neptun));
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Get Student (neptun: " + neptun + ") --> " + stub);
			}
			return stub;
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new AdaptorException(ApplicationError.UNEXPECTED, e.getLocalizedMessage());
		}
	}

	@Override
	public List<StudentStub> getAllStudents() throws AdaptorException {
		List<StudentStub> stubs = new ArrayList<>();
		try {
			stubs = this.converter.to(this.studentService.readAll());
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Get all Students --> " + stubs.size() + " item(s)");
			}
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new AdaptorException(ApplicationError.UNEXPECTED, e.getLocalizedMessage());
		}
		return stubs;
	}

	@Override
	public void removeStudent(final String neptun) throws AdaptorException {
		try {
			if (this.studentService.exists(neptun)) {
				if (this.markService.count(neptun) == 0) {
					this.studentService.delete(neptun);
				} else {
					throw new AdaptorException(ApplicationError.HAS_DEPENDENCY, "Student has undeleted mark(s)", neptun);
				}
			} else {
				throw new AdaptorException(ApplicationError.NOT_EXISTS, "Student doesn't exist", neptun);
			}
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new AdaptorException(ApplicationError.UNEXPECTED, e.getLocalizedMessage());
		}
	}

	@Override
	public void removeStudentAdvanced(final String neptun) throws AdaptorException {
		try {
			this.studentService.deleteAdvanced(neptun);
		} catch (final AdvancedPersistenceServiceException e) {
			final ApplicationError error = ApplicationError.valueOf(e.getError().name());
			throw new AdaptorException(error, e.getLocalizedMessage(), e.getField());
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new AdaptorException(ApplicationError.UNEXPECTED, e.getLocalizedMessage());
		}
	}

}
