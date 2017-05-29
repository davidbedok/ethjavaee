package com.ericsson.bookstore.diskservice.exception;

public class DiskServiceException extends Exception {

	private static final long serialVersionUID = 6791823269155412515L;

	public DiskServiceException(String message) {
		super(message);
	}

	public DiskServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
