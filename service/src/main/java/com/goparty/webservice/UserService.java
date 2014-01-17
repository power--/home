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
	StringResponse uploadImage(MultipartBody image, @PathParam("id") String userId) ;
	
	@WebMethod
	@GET
	@Path("{userId}/events")
	@Descriptions({
		@Description(value = "read events of an user", target = DocTarget.METHOD),
		@Description(value = "events of an user", target = DocTarget.RETURN)
	})
	
	public List<Event> events(@Description(value = "the userId of the events") @PathParam("userId") String userId, 
			@Description(value = "the offset") @QueryParam("offset") int offset,
			@Description(value = "the limit")  @QueryParam("limit") int limit,
			@Description(value = "the range")  @HeaderParam("Range") String range,
			@Description(value = "the filter") @QueryParam("filter") String filter,
			@Description(value = "the sorter") @QueryParam("sort") String sort
			
	);
	
	@GET
	@Path("{userId}/friends/{friendId}")
	public boolean addFriend(@PathParam("userId")String userId, @PathParam("friendId")String friendId);
	
	@GET
	@Path("{userId}/friends")
	public List<User> getFriends(@PathParam("userId")String userId);
}
