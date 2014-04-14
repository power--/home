package com.goparty.webservice;

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

import com.goparty.webservice.model.FriendInvitationRequest;
import com.goparty.webservice.model.FriendRequest;
import com.goparty.webservice.model.GroupRequest;

@Path("/friends/")
@WebService
@GZIP
public interface FriendService { 
	
	@POST
	@Path("{friendId}") 
	public Response invite(@HeaderParam("token") String token,  @PathParam("friendId") String friendId, FriendInvitationRequest request);
	
	
	@PUT
	@Path("{friendId}") 
	public Response update(@HeaderParam("token") String token, @PathParam("friendId") String friendId, FriendRequest request);	
	
	
	@DELETE
	@Path("{friendId}") 
	public Response delete(@HeaderParam("token") String token,  @PathParam("friendId") String friendId);	
	
	@GET 
	public Response getFriends(@HeaderParam("token") String token,@QueryParam("offset") int offset,@QueryParam("limit") int limit);	 
	
	
	
	@GET
	@Path("unrespondedInvitations")
	public Response getUnRespInvitations(@HeaderParam("token") String token,@QueryParam("offset") int offset,@QueryParam("limit") int limit);
		
	
	@PUT
	@Path("unrespondedInvitations/{invitationId}")
	public Response respondInvitation(@HeaderParam("token") String token,  @PathParam("invitationId") String invitationId, FriendInvitationRequest request);
	
	@GET
	@Path("respondedInvitations")
	public Response getRespInvitations(@HeaderParam("token") String token,@QueryParam("offset") int offset,@QueryParam("limit") int limit);
	
 
	@POST
	@Path("groups") 
	public Response addGroup(@HeaderParam("token") String token,  GroupRequest request);
	
	@GET
	@Path("groups") 
	public Response getGroups(@HeaderParam("token") String token);
	
	
	@PUT
	@Path("groups/{groupId}") 
	public Response updateGroup(@HeaderParam("token") String token,  @PathParam("groupId") String groupId,  GroupRequest request);
	
	@DELETE
	@Path("groups/{groupId}") 
	public Response deleteGroup(@HeaderParam("token") String token,@PathParam("groupId") String groupId);
}
