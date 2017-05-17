package com.ericsson.lottery.webservice.mapper;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ericsson.lottery.ejbservice.exception.AdaptorException;
import com.ericsson.lottery.webservice.filter.LotteryCrossOriginRequestFilter;

@Provider
public class AdaptorExceptionMapper implements ExceptionMapper<AdaptorException> {

	@Context
	private HttpHeaders headers;

	@Override
	public Response toResponse(AdaptorException e) {
		return Response.status(500).entity(e.build()) //
				.header(LotteryCrossOriginRequestFilter.ALLOW_ORIGIN, "*") //
				.header(LotteryCrossOriginRequestFilter.ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS, HEAD") //
				.header(LotteryCrossOriginRequestFilter.MAX_AGE, "1209600") //
				.header(LotteryCrossOriginRequestFilter.ALLOW_HEADERS, "x-requested-with, origin, content-type, accept, X-Codingpedia, authorization") //
				.header(LotteryCrossOriginRequestFilter.ALLOW_CREDENTIALS, "true") //
				.type(this.headers.getMediaType()).build();
	}

}
