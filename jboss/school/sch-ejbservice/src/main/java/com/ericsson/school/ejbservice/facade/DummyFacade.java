package com.ericsson.school.ejbservice.facade;

import javax.ejb.Local;

@Local
public interface DummyFacade {

	int addSmallPositiveNumbers(String left, String right);

}
