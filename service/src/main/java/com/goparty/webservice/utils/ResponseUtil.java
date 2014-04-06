package com.goparty.webservice.utils;

import javax.ws.rs.core.Response;
 

public class ResponseUtil {

	public static Response buildResponse(Object response) {
		BaseData data = new BaseData();
		data.setCode(200);
		data.setStatus("success");
		data.setData(response);
		return Response.ok(data).build();
	}
	
	public static Response buildResponse(Object response, String token) {
		BaseData data = new BaseData();
		data.setCode(200);
		data.setStatus("success");
		data.setData(response);
		return Response.ok(data).header("token", token).build();
	}

}

