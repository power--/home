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
import com.goparty.webservice.model.InvitationRequest;

@Path("/friends/")
@WebService
@GZIP
public interface FriendService { 
	
	@POST
	@Path("{friendId}") 
	public boolean add(@HeaderParam("token") String token,  @PathParam("friendId") String friendId);
	
	
	@PUT
	@Path("{friendId}") 
	public UserFriend update(@HeaderParam("token") String token, UserFriend uf);	
	
	
	@DELETE
	@Path("{friendId}") 
	public boolean delete(@HeaderParam("token") String token,  @PathParam("friendId") String friendId);	
	
	
	
	
	@GET
	@Path("invitations")
	public List<UserFriend> getFriendInvitationList(@HeaderParam("token") String token);
		
	
	@PUT
	@Path("invitations/{friendId}")
	public boolean respondFriendInvitation(@HeaderParam("token") String token,  @PathParam("friendId") String friendId, InvitationRequest request);
	
 
	
}
