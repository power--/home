package com.goparty.ex;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;

public class BaseExceptionMapper implements ExceptionMapper<BaseException>{

	@Override
	public Response toResponse(BaseException exception) {
		ResponseBuilder rb = Response.status(exception.getStatus());
		rb.header("code", exception.getCode());
		rb.header("message",exception.getMessage());
		rb.header("data", exception.getData());
		
		return rb.build();
	}
}
