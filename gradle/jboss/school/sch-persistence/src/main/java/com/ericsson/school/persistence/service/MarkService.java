package com.ericsson.school.persistence.service;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.school.persistence.entity.Mark;
import com.ericsson.school.persistence.exception.PersistenceServiceException;
import com.ericsson.school.persistence.result.MarkDetailResult;

@Local
public interface MarkService {

	int count(String studentNeptun) throws PersistenceServiceException;

	Mark create(Long studentId, Long subjectId, Integer grade, String note) throws PersistenceServiceException;

	List<MarkDetailResult> read(Long subjectId) throws PersistenceServiceException;

	Mark read(String studentNeptun, String subjectNameTerm, Integer minimumGrade, Integer maximumGrade) throws PersistenceServiceException;

}
