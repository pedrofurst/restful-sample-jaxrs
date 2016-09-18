package com.sample.exception;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.sample.model.Error;

@Provider
public class UserExceptionMapper implements ExceptionMapper<UserException> {

	@Override
	public Response toResponse(UserException exception) {
		return Response.status( BAD_REQUEST )
				.entity( new Error( exception.getMessage() ) )
				.type( APPLICATION_JSON )
				.build();
	}
}
