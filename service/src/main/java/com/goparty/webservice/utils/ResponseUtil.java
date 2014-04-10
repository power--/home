package com.goparty.webservice.utils;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;

import com.goparty.data.exception.BaseException;
 

public class ResponseUtil {

	public static Response buildResponse(Object response) {
		BaseData data = new BaseData();
		data.setCode(200);
		data.setStatus("success");
		
		ObjectMapper mapper = new ObjectMapper();        
        mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);            
        String json = "";
		try {
			json = mapper.writeValueAsString(response);
		} catch (Exception e) {
			throw new BaseException("write json error!");
		}
		data.setData(json);
		return Response.ok(data).build();
	}
	
	public static Response buildResponse(Object response, String token) {
		BaseData data = new BaseData();
		data.setCode(200);
		data.setStatus("success");
		ObjectMapper mapper = new ObjectMapper();        
        mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);            
        String json = "";
		try {
			json = mapper.writeValueAsString(response);
		} catch (Exception e) {
			throw new BaseException("write json error!");
		}
		data.setData(json);
		return Response.ok(data).header("token", token).build();
	}

}

