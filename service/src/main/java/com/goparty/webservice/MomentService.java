package com.goparty.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.cxf.annotations.GZIP;

@Path("/moments/")
@WebService
@GZIP
public interface MomentService {
	
	

	@WebMethod
	@GET
	@Path("{momentId}")
	public Response read(@HeaderParam("token") String token, @PathParam("momentId") String momentId);
	
	

}
