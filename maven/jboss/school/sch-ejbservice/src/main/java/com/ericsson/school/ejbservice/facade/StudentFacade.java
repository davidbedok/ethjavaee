package com.ericsson.school.ejbservice.facade;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.school.ejbservice.domain.StudentStub;
import com.ericsson.school.ejbservice.exception.AdaptorException;

@Local
public interface StudentFacade {

	StudentStub getStudent(String neptun) throws AdaptorException;

	List<StudentStub> getAllStudents() throws AdaptorException;

	List<StudentStub> getStudents(int pageSize, int page) throws AdaptorException;

	void removeStudent(String neptun) throws AdaptorException;

	void removeStudentAdvanced(String neptun) throws AdaptorException;

}
