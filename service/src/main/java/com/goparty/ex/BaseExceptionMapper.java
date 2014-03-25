package com.goparty.ex;
 
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;

import com.goparty.data.exception.BaseException; 
import com.goparty.webservice.model.ExceptionResponse;

public class BaseExceptionMapper implements ExceptionMapper<RuntimeException>{

	@Override
	public Response toResponse(RuntimeException exception) {
		ResponseBuilder rb = Response.status(Response.Status.OK);
		ExceptionResponse resp = new ExceptionResponse();
//		int code = exception.getCode();
//		if(code == 0){
//			resp.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
//		}
		resp.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		resp.setMessage(exception.getMessage());
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		resp.setData(sw.toString());
		
		rb.type("application/json;charset=UTF-8");
		rb.entity(resp);		
		return rb.build();
	}
}
