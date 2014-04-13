package com.goparty.data.exception;
 

import javax.ws.rs.WebApplicationException;   

public class BaseException extends WebApplicationException{ 

	private static final long serialVersionUID = 1L;
	
	private int code;
	private String message; 
	
	public BaseException(String message){ 
		this.message = message;
	}
	
	
	public BaseException(int code, String message) {  
		this.code = code;
		this.message = message; 
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
  
}
