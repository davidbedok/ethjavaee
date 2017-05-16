package com.ericsson.school.persistence.service;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.school.persistence.entity.Student;
import com.ericsson.school.persistence.exception.PersistenceServiceException;

@Local
public interface StudentService {

	boolean exists(String neptun) throws PersistenceServiceException;

	Student read(Long id) throws PersistenceServiceException;

	Student read(String neptun) throws PersistenceServiceException;

	List<Student> readAll() throws PersistenceServiceException;

	void delete(String neptun) throws PersistenceServiceException;

	void deleteAdvanced(String neptun) throws PersistenceServiceException;

}
