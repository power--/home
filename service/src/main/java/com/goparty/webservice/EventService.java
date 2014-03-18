package com.goparty.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import com.goparty.data.model.*; 
import com.goparty.webservice.model.CommentRequest;
import com.goparty.webservice.model.MessageRequest;

@Path("/events/")
@WebService
@GZIP
public interface EventService {
	
	
	@WebMethod
	@GET
	@Path("{id}")
	@Descriptions({
		@Description(value = "returns a event by id", target = DocTarget.METHOD),
		@Description(value = "event of the id", target = DocTarget.RETURN)
	})
	public Event read(@Description(value = "the id of the event") @PathParam("id") String id);
	
	@WebMethod
	@POST
	@Descriptions({
		@Description(value = "stores a new event data", target = DocTarget.METHOD),
		@Description(value = "the newly created event data", target = DocTarget.RETURN)
	})
	public Event create(Event event);
	
	@WebMethod
	@PUT
	@Descriptions({
		@Description(value = "updates or creates a new event data", target = DocTarget.METHOD),
		@Description(value = "the newly created or updated event data", target = DocTarget.RETURN)
	})
	public Event update(Event event);
	
	@WebMethod
	@DELETE
	@Path("{id}")
	@Descriptions({
		@Description(value = "deletes a user data", target = DocTarget.METHOD),
		@Description(value = "the result of delete user action", target = DocTarget.RETURN)
	})
	public boolean delete(@Description(value = "the id of the event") @PathParam("id")String id);
	
	
	@WebMethod
	@PUT
	@Path("{eventId}/invitees/{userId}")
	@Descriptions({
		@Description(value = "invite a user to join the event", target = DocTarget.METHOD),
		@Description(value = "the result of invite user action", target = DocTarget.RETURN)
	})
	public BaseModel addInvitee(@PathParam("eventId") String eventId, @PathParam("userId")String userId);
	
	
	@WebMethod
	@DELETE
	@Path("{eventId}/invitees/{userId}")
	@Descriptions({
		@Description(value = "invite a user to join the event", target = DocTarget.METHOD),
		@Description(value = "the result of invite user action", target = DocTarget.RETURN)
	})
	public BaseModel delInvitee(@PathParam("eventId") String eventId, @PathParam("userId")String userId);
	
	
	@WebMethod
	@PUT
	@Path("{eventId}/sponsors/{userId}")
	@Descriptions({
		@Description(value = "invite a user to join the event", target = DocTarget.METHOD),
		@Description(value = "the result of invite user action", target = DocTarget.RETURN)
	})
	public BaseModel updateSponser(@PathParam("eventId") String eventId, @PathParam("userId")String userId);
	
	
	@WebMethod
	@POST
	@Path("{eventId}/messages")
	public EventMessage publishMessage(@HeaderParam("token") String token,@PathParam("eventId") String eventId, MessageRequest request);
	
	@WebMethod
	@GET
	@Path("{eventId}/messages")
	public List<EventMessage> getMessageListByEventId(@PathParam("eventId") String eventId,@QueryParam("offset") int offset,@QueryParam("limit") int limit);
	
	@WebMethod
	@POST
	@Path("{eventId}/comments")
	public EventComment comment(@HeaderParam("token") String token,@PathParam("eventId") String eventId, CommentRequest request);
	
	@WebMethod
	@GET
	@Path("{eventId}/comments")
	public List<EventComment> getCommentListByEventId(@PathParam("eventId") String eventId,@QueryParam("offset") int offset,@QueryParam("limit") int limit);
	
	
}
