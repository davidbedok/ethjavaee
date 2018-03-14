package hu.qwaevisz.lottery.webservice.mapper;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import hu.qwaevisz.lottery.ejbservice.exception.AdaptorException;
import hu.qwaevisz.lottery.webservice.filter.LotteryCORSFilter;

@Provider
public class AdaptorExceptionMapper implements ExceptionMapper<AdaptorException> {

	@Context
	private HttpHeaders headers;

	@Override
	public Response toResponse(AdaptorException e) {
		return Response.status(500).entity(e.build()) //
				.header(LotteryCORSFilter.ALLOW_ORIGIN, "*") //
				.header(LotteryCORSFilter.ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS, HEAD") //
				.header(LotteryCORSFilter.MAX_AGE, "1209600") //
				.header(LotteryCORSFilter.ALLOW_HEADERS, "x-requested-with, origin, content-type, accept, X-Codingpedia, authorization") //
				.header(LotteryCORSFilter.ALLOW_CREDENTIALS, "true") //
				.type(this.headers.getMediaType()).build();
	}

}
