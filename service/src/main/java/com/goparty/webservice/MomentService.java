package com.goparty.webservice;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.apache.cxf.annotations.GZIP;

import com.goparty.webservice.model.MomentCommentRequest;
import com.goparty.webservice.model.MomentRequest;

@Path("/moments/")
@WebService
@GZIP
public interface MomentService {
	
	

	@WebMethod
	@GET
	@Path("{momentId}")
	public Response read(@HeaderParam("token") String token, @PathParam("momentId") String momentId);
	
	
	@WebMethod
	@DELETE
	@Path("{momentId}")
	public Response delete(@HeaderParam("token") String token, @PathParam("momentId") String momentId);
	
	@WebMethod
	@POST
	@Path("{momentId}/comments")
	public Response addComment(@HeaderParam("token") String token, @PathParam("momentId") String momentId, MomentCommentRequest request);
	
	

	@WebMethod
	@GET
	@Path("{momentId}/comments")
	public Response getCommentsList(@HeaderParam("token") String token, @PathParam("momentId") String momentId, int offset, int limit,Date before, Date after, String keyword);
	
}
