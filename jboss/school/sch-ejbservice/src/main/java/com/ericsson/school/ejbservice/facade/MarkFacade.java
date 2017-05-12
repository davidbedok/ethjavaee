package com.ericsson.school.ejbservice.facade;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.school.ejbservice.domain.MarkDetailStub;
import com.ericsson.school.ejbservice.domain.MarkStub;
import com.ericsson.school.ejbservice.exception.AdaptorException;

@Local
public interface MarkFacade {

	List<MarkDetailStub> getMarkDetails(String subject) throws AdaptorException;

	MarkStub addMark(String subject, String neptun, int grade, String note) throws AdaptorException;

	MarkStub getMatchingMark(String studentNeptun, String subjectNameTerm, int minimumGrade, int maximumGrade) throws AdaptorException;

}
