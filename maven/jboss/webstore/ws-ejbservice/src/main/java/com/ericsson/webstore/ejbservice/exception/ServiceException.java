package com.ericsson.webstore.ejbservice.exception;

import com.ericsson.webstore.ejbservice.domain.ServiceError;

public class ServiceException extends Exception {

	private static final long serialVersionUID = -6072617107159960432L;

	private final int code;

	public ServiceException(WebStoreError error, String message) {
		this(error.getCode(), message, null);
	}

	public ServiceException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public ServiceError getError() {
		return new ServiceError(this.code, this.getMessage());
	}

}
