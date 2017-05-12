package com.ericsson.school.ejbservice.converter;

import javax.ejb.Stateless;

import com.ericsson.school.ejbservice.exception.ConverterException;

@Stateless
public class DummyConverterImpl implements DummyConverter {

	@Override
	public int toNumber(String value) throws ConverterException {
		int result = 0;
		switch (value) {
			case "one":
				result = 1;
				break;
			case "two":
				result = 2;
				break;
			case "three":
				result = 3;
				break;
			default:
				throw new ConverterException();
		}
		return result;
	}

}
