package com.ericsson.school.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.school.ejbservice.domain.StudentStub;
import com.ericsson.school.persistence.entity.Student;

@Local
public interface StudentConverter {

	StudentStub to(Student student);

	List<StudentStub> to(List<Student> students);

}
