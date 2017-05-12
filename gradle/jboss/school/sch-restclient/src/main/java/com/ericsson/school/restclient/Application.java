package com.ericsson.school.restclient;

import com.ericsson.school.restclient.domain.MarkCriteria;
import com.ericsson.school.restclient.domain.MarkStub;

public class Application {

	private static final String HOST = "localhost";
	private static final int PORT = 8080;

	public static void main(String[] args) {
		final SchoolRestClient client = new SchoolRestClient(HOST, PORT);
		final MarkStub mark = client.process("WI53085", new MarkCriteria("Python", 2, 4));
		System.out.println(mark);
	}

}
