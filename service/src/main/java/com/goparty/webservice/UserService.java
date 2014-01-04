package com.goparty.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import com.goparty.biz.model.*;

@Path("/user/")
@WebService
@GZIP
public interface UserService {
	
	
	@WebMethod
	@GET
	@Path("{id}")
	@Descriptions({
		@Description(value = "returns a user by id", target = DocTarget.METHOD),
		@Description(value = "user of the id", target = DocTarget.RETURN)
	})
	public User read(@Description(value = "the id of the user") @PathParam("id") String id);
	
	@WebMethod
	@POST
	@Descriptions({
		@Description(value = "stores a new user data", target = DocTarget.METHOD),
		@Description(value = "the newly created user data", target = DocTarget.RETURN)
	})
	public User create(User user);
	
	@WebMethod
	@PUT
	@Descriptions({
		@Description(value = "updates or creates a new user data", target = DocTarget.METHOD),
		@Description(value = "the newly created or updated user data", target = DocTarget.RETURN)
	})
	public User update(User user);
	
	@WebMethod
	@DELETE
	@Path("{id}")
	@Descriptions({
		@Description(value = "deletes a user data", target = DocTarget.METHOD),
		@Description(value = "the result of delete user action", target = DocTarget.RETURN)
	})
	public boolean delete(@Description(value = "the id of the user") @PathParam("id")String id);
	
	@WebMethod
	@POST
	@Path("{id}/image")
	//@Consumes("multipart/mixed")
	@Consumes("multipart/form-data")
	String uploadImage(@Multipart(value = "image" , type = "image/*") Attachment image, @PathParam("id") String userId) ;
}
