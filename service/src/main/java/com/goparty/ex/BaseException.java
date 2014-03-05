package com.goparty.ex;

import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class BaseException extends WebApplicationException{
	private Status status;
	private String code;
	private String message;
	private Map<String, String> data;
	
	public BaseException(Status status, String code, String message,
			Map<String, String> data) {
		super(Response.status(status).header("exception_code", code).header("exception_message", message)
				                     .entity(data).build());
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public BaseException(Status status, String code, String message) {
		this(status,  code,  message,null);
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}
}
