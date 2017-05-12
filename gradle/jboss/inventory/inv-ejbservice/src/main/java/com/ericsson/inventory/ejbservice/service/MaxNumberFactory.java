package com.ericsson.inventory.ejbservice.service;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.ericsson.inventory.ejbservice.interceptor.Logged;
import com.ericsson.inventory.ejbservice.qualifier.MaxNumber;

@ApplicationScoped
public class MaxNumberFactory {

	@Resource(lookup = "java:global/maxNumber")
	private int maxValue;

	@Logged
	@Produces
	@MaxNumber
	public int getMaxNumber() {
		return this.maxValue;
	}

}
