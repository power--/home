package com.goparty.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import com.goparty.data.model.*;
import com.goparty.webservice.model.LoginRequest;

@Path("/")
@WebService
@GZIP
public interface UserService { 
	
	@POST
	@Path("login") 
	public User login(@HeaderParam("token") String token, LoginRequest request);
	
	
	@POST
	@Path("logout")
	public boolean logout( @HeaderParam("token") String token);	
	
	
	@GET
	@Path("profile")
	public User getProfile(@Description(value = "the id of the user") @HeaderParam("token") String token);
		
	
	@PUT
	@Path("profile")
	public User updateProfile(User user);
	

	
	@GET
	@Path("users/{userId}")
	@Descriptions({
		@Description(value = "returns a user by id", target = DocTarget.METHOD),
		@Description(value = "user of the id", target = DocTarget.RETURN)
	})
	public User getUserInfo(@Description(value = "the id of the user") @PathParam("userId") String userId);
	
	
	@GET
	@Path("users") 
	public List<User> search(@QueryParam("search") String search,@QueryParam("offset") int offset,@QueryParam("limit") int limit);
	 
	
	@POST
	@Path("{id}/image")
	//@Consumes("multipart/mixed")
	@Consumes("multipart/form-data")
	StringResponse uploadImage(MultipartBody image, @PathParam("id") String userId) ;
	
}
