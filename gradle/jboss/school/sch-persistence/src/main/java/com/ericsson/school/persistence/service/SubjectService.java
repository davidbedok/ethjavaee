package com.ericsson.school.persistence.service;

import javax.ejb.Local;

import com.ericsson.school.persistence.entity.Subject;
import com.ericsson.school.persistence.exception.PersistenceServiceException;

@Local
public interface SubjectService {

	Subject read(Long id) throws PersistenceServiceException;

	Subject read(String name) throws PersistenceServiceException;

}
