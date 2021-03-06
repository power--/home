package com.goparty.webservice;

import java.util.Date;

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
import javax.ws.rs.core.Response;

import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import com.goparty.data.model.Event;
import com.goparty.webservice.model.CommentRequest;
import com.goparty.webservice.model.EventApplicationRequest;
import com.goparty.webservice.model.EventApplierRequest;
import com.goparty.webservice.model.EventInvitationRequest;
import com.goparty.webservice.model.EventInviteRequest;
import com.goparty.webservice.model.MessageRequest;
import com.goparty.webservice.model.MomentRequest;

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
	public Response read(@Description(value = "the id of the event") @PathParam("id") String id);
	
	@WebMethod
	@POST
	@Descriptions({
		@Description(value = "stores a new event data", target = DocTarget.METHOD),
		@Description(value = "the newly created event data", target = DocTarget.RETURN)
	})
	public Response create(Event request);
	
	@WebMethod
	@PUT
	@Descriptions({
		@Description(value = "updates or creates a new event data", target = DocTarget.METHOD),
		@Description(value = "the newly created or updated event data", target = DocTarget.RETURN)
	})
	public Response update(Event event);
	
	@WebMethod
	@DELETE
	@Path("{id}")
	@Descriptions({
		@Description(value = "deletes a event data", target = DocTarget.METHOD),
		@Description(value = "the result of delete event action", target = DocTarget.RETURN)
	})
	public Response delete(@Description(value = "the id of the event") @PathParam("id")String id);
	
	
	@WebMethod
	@PUT
	@Path("{eventId}/invitees/{userId}")
	@Descriptions({
		@Description(value = "invite a user to join the event", target = DocTarget.METHOD),
		@Description(value = "the result of invite user action", target = DocTarget.RETURN)
	})
	public Response addInvitee(@PathParam("eventId") String eventId, @PathParam("userId")String userId);
	
	
	@WebMethod
	@DELETE
	@Path("{eventId}/invitees/{userId}")
	@Descriptions({
		@Description(value = "invite a user to join the event", target = DocTarget.METHOD),
		@Description(value = "the result of invite user action", target = DocTarget.RETURN)
	})
	public Response delInvitee(@PathParam("eventId") String eventId, @PathParam("userId")String userId);
	
	
	@WebMethod
	@PUT
	@Path("{eventId}/sponsors/{userId}")
	@Descriptions({
		@Description(value = "invite a user to join the event", target = DocTarget.METHOD),
		@Description(value = "the result of invite user action", target = DocTarget.RETURN)
	})
	public Response updateSponser(@PathParam("eventId") String eventId, @PathParam("userId")String userId);
	
	
	@WebMethod
	@POST
	@Path("{eventId}/messages")
	public Response publishMessage(@HeaderParam("token") String token,@PathParam("eventId") String eventId, MessageRequest request);
	
	@WebMethod
	@GET
	@Path("{eventId}/messages")
	public Response getMessageListByEventId(@PathParam("eventId") String eventId,@QueryParam("offset") int offset,@QueryParam("limit") int limit);
	
	@WebMethod
	@POST
	@Path("{eventId}/comments")
	public Response comment(@HeaderParam("token") String token,@PathParam("eventId") String eventId, CommentRequest request);
	
	@WebMethod
	@GET
	@Path("{eventId}/comments")
	public Response getCommentListByEventId(@PathParam("eventId") String eventId,@QueryParam("offset") int offset,@QueryParam("limit") int limit);
	
	@WebMethod
	@GET
	@Descriptions({
		@Description(value = "return events", target = DocTarget.METHOD),
		@Description(value = "return events", target = DocTarget.RETURN)
	})
	@Path("")
	public Response getEvents(@HeaderParam("token") String token, @PathParam("scope") String scope,@QueryParam("after") Date after, @QueryParam("before") Date before, @QueryParam("categories") String categories, @QueryParam("search") String search, @QueryParam("offset") long offset, @QueryParam("limit") long limit);

	@WebMethod
	@GET
	@Descriptions({
		@Description(value = "return events", target = DocTarget.METHOD),
		@Description(value = "return events", target = DocTarget.RETURN)
	})
	@Path("/users/{userId}/events?after={after}&before={before}&categories={categories}&search={search}&offset={offset}&limit={limit}")
	public Response getUserEvents(@HeaderParam("token") String token,@PathParam("userId") String userId,@QueryParam("after") Date after, @QueryParam("before") Date before, @QueryParam("categories") String categories, @QueryParam("search") String search, @QueryParam("offset") long offset, @QueryParam("limit") long limit);
	
	
	@WebMethod
	@POST
	@Path("{eventId}/members/{userIds}")
	public Response inviteUserJoinEvent(@HeaderParam("token") String token,@PathParam("eventId") String eventId,@PathParam("userIds") String userIds, EventInviteRequest request);
	
	@WebMethod
	@POST
	@Path("{eventId}/member")
	public Response applyEvent(@HeaderParam("token") String token,@PathParam("eventId") String eventId, EventApplierRequest request);
	
	@WebMethod
	@GET
	@Path("unrespondedInvitations")
	public Response getUnrespondedInvitations( @HeaderParam("token") String token, @QueryParam("offset") long offset, @QueryParam("limit") long limit);
	
	@WebMethod
	@PUT
	@Path("unrespondedInvitations/{invitationId}")
	public Response respondedInvitations( @HeaderParam("token") String token, @PathParam("invitationId") String  invitationId,EventInvitationRequest request);
	
	@WebMethod
	@GET
	@Path("respondedInvitations")
	public Response getRespondedInvitations( @HeaderParam("token") String token, @QueryParam("offset") long offset, @QueryParam("limit") long limit);
	
	@WebMethod
	@GET
	@Path("unrespondedApplications")
	public Response getUnrespondedApplications( @HeaderParam("token") String token, @QueryParam("offset") long offset, @QueryParam("limit") long limit);
	
	@WebMethod
	@PUT
	@Path("unrespondedApplications/{applicationId}")
	public Response respondedApplications( @HeaderParam("token") String token, @PathParam("applicationId") String  applicationId,EventApplicationRequest request);
	
	@WebMethod
	@GET
	@Path("respondedApplications")
	public Response getRespondedApplications( @HeaderParam("token") String token, @QueryParam("offset") long offset, @QueryParam("limit") long limit);
	
	@WebMethod
	@POST
	@Path("{eventId}/moments")
	public Response createMoment(@PathParam("eventId") String eventId, @HeaderParam("token") String token, MomentRequest request);
	
	@WebMethod
	@GET
	@Path("{eventId}/moments")
	public Response listMoments(@PathParam("eventId") String eventId, @HeaderParam("token") String token, @QueryParam("offset") int offset,@QueryParam("limit") int limit, @QueryParam("before") Date before, @QueryParam("after") Date after, @QueryParam("keyword") String keyword);
}
	
