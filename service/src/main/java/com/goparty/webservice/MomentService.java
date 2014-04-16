package com.goparty.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.cxf.annotations.GZIP;

import com.goparty.webservice.model.MomentRequest;

@Path("/mevents/")
@WebService
@GZIP
public interface MomentService {
	
	@WebMethod
	@POST
	@Path("{eventId}/moments")
	public Response create(@PathParam("eventId") String eventId, @HeaderParam("token") String token, MomentRequest request);
}
