package com.ericsson.school.ejbservice.converter;

import javax.ejb.Local;

import com.ericsson.school.ejbservice.exception.ConverterException;

@Local
public interface DummyConverter {

	int toNumber(String value) throws ConverterException;

}
