package com.ericsson.school.ejbservice.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.ericsson.school.ejbservice.converter.DummyConverter;
import com.ericsson.school.ejbservice.exception.ConverterException;

@Stateless(mappedName = "ejb/dummyFacade")
public class DummyFacadeImpl implements DummyFacade {

	@EJB
	private DummyConverter converter;

	@Override
	public int addSmallPositiveNumbers(String left, String right) {
		int result = 0;
		try {
			result = this.converter.toNumber(left) + this.converter.toNumber(right);
		} catch (ConverterException e) {
			result = -1;
		}
		return result;
	}

	protected void setConverter(DummyConverter converter) {
		this.converter = converter;
	}

}
