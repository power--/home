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
import com.goparty.data.vo.FriendInvitatinVo;
import com.goparty.webservice.model.FriendRequest;
import com.goparty.webservice.model.FriendResponse;
import com.goparty.webservice.model.InvitationRequest;

@Path("/friends/")
@WebService
@GZIP
public interface FriendService { 
	
	@POST
	@Path("{friendId}") 
	public boolean invite(@HeaderParam("token") String token,  @PathParam("friendId") String friendId, FriendRequest request);
	
	
	@PUT
	@Path("{friendId}") 
	public UserFriend update(@HeaderParam("token") String token, @PathParam("friendId") String friendId, FriendRequest request);	
	
	
	@DELETE
	@Path("{friendId}") 
	public boolean delete(@HeaderParam("token") String token,  @PathParam("friendId") String friendId);	
	
	@GET 
	public List<FriendResponse> getFriends(@HeaderParam("token") String token,@QueryParam("offset") int offset,@QueryParam("limit") int limit);	 
	
	
	
	@GET
	@Path("unrespondedInvitations")
	public List<FriendInvitatinVo> getUnRespInvitations(@HeaderParam("token") String token,@QueryParam("offset") int offset,@QueryParam("limit") int limit);
		
	
	@PUT
	@Path("unrespondedInvitations/{invitationId}")
	public boolean respondInvitation(@HeaderParam("token") String token,  @PathParam("invitationId") String invitationId, InvitationRequest request);
	
	@GET
	@Path("respondedInvitations")
	public List<FriendInvitatinVo> getRespInvitations(@HeaderParam("token") String token,@QueryParam("offset") int offset,@QueryParam("limit") int limit);
	
 
	@POST
	@Path("groups") 
	public Group addGroup(@HeaderParam("token") String token,  FriendRequest request);
	
	@PUT
	@Path("groups/{groupId}") 
	public Group updateGroup(@HeaderParam("token") String token,  @PathParam("groupId") String groupId,  FriendRequest request);
	
	@DELETE
	@Path("groups/{groupId}") 
	public boolean deleteGroup(@HeaderParam("token") String token,@PathParam("groupId") String groupId);
}
