package com.ericsson.webstore.webservice.exception;

import javax.xml.ws.WebFault;

import com.ericsson.webstore.ejbservice.domain.ServiceError;

@WebFault(name = "WebStoreServiceFault", targetNamespace = "http://www.ericsson.com/WebStore")
public class WebStoreServiceException extends Exception {

	private static final long serialVersionUID = 536014448507939548L;

	private final ServiceError faultInfo;

	public WebStoreServiceException(String message, ServiceError faultInfo) {
		super(message);
		this.faultInfo = faultInfo;
	}

	public WebStoreServiceException(String message, ServiceError faultInfo, Throwable cause) {
		super(message, cause);
		this.faultInfo = faultInfo;
	}

	public ServiceError getFaultInfo() {
		return this.faultInfo;
	}

}
