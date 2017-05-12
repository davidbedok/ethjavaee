package com.ericsson.school.ejbservice.facade;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ericsson.school.ejbservice.converter.DummyConverter;
import com.ericsson.school.ejbservice.converter.DummyConverterImpl;
import com.ericsson.school.ejbservice.exception.ConverterException;

public class DummyFacadeImplTest {

	@Test
	public void addTwoSmallPositiveNumberAndGetTheRightSolution() {
		DummyFacadeImpl facade = new DummyFacadeImpl();
		facade.setConverter(new DummyConverterImpl());
		int result = facade.addSmallPositiveNumbers("one", "three");
		Assert.assertEquals(result, 4);
	}

	@Test
	public void addTwoAnotherSmallPositiveNumberAndGetTheRightSolution() {
		DummyFacadeImpl facade = new DummyFacadeImpl();
		facade.setConverter(new DummyConverterImpl());
		int result = facade.addSmallPositiveNumbers("two", "five");
		Assert.assertEquals(result, 7);
	}

	@Test
	public void addTwoSmallPositiveNumberAndGetTheRightSolutionWithMock() throws ConverterException {
		DummyFacadeImpl facade = new DummyFacadeImpl();
		DummyConverter converter = Mockito.mock(DummyConverter.class);
		facade.setConverter(converter);

		Mockito.when(converter.toNumber("two")).thenReturn(2);
		Mockito.when(converter.toNumber("five")).thenReturn(5);

		int result = facade.addSmallPositiveNumbers("two", "five");
		Assert.assertEquals(result, 7);
	}

	@InjectMocks
	private DummyFacadeImpl facade;

	@Mock
	private DummyConverter converter;

	@BeforeMethod
	public void setup() throws ConverterException {
		MockitoAnnotations.initMocks(this);
		Mockito.when(this.converter.toNumber("two")).thenReturn(2);
		Mockito.when(this.converter.toNumber("five")).thenReturn(5);
	}

	@Test
	public void addTwoSmallPositiveNumberAgain() throws ConverterException {
		Assert.assertEquals(this.facade.addSmallPositiveNumbers("two", "five"), 7);
	}

}
