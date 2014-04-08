package com.goparty.webservice;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import com.goparty.webservice.model.LoginRequest;
import com.goparty.webservice.model.UserRequest;

@Path("/")
@WebService
@GZIP
public interface UserService { 
	
	@POST
	@Path("login") 
	public Response login(@HeaderParam("token") String token, LoginRequest request);
	
	
	@PUT
	@Path("logout")
	public Response logout( @HeaderParam("token") String token);	
	
	
	@GET
	@Path("profile")
	public Response getProfile(@Description(value = "the id of the user") @HeaderParam("token") String token);
		
	
	@PUT
	@Path("profile")
	public Response updateProfile(@HeaderParam("token") String token,UserRequest request);
	

	
	@GET
	@Path("users/{userId}")
	@Descriptions({
		@Description(value = "returns a user by id", target = DocTarget.METHOD),
		@Description(value = "user of the id", target = DocTarget.RETURN)
	})
	public Response getUserInfo(@Description(value = "the id of the user") @PathParam("userId") String userId);
	
	
	@GET
	@Path("users") 
	public Response search(@QueryParam("search") String search,@QueryParam("offset") int offset,@QueryParam("limit") int limit);
	 
	
	@POST
	@Path("{id}/image")
	//@Consumes("multipart/mixed")
	@Consumes("multipart/form-data")
	Response uploadImage(MultipartBody image, @PathParam("id") String userId) ;
	
}
