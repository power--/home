package com.goparty.webservice.impl;
  
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.goparty.data.constant.InvitationAcceptance;
import com.goparty.data.constant.InvitationStatus;
import com.goparty.data.exception.BaseException;
import com.goparty.data.model.FriendInvitation;
import com.goparty.data.model.Group;
import com.goparty.data.model.User;
import com.goparty.data.model.UserFriend; 
import com.goparty.data.model.UserFriendPK;
import com.goparty.data.service.FriendDataService; 
import com.goparty.data.service.UserDataService;
import com.goparty.data.vo.FriendInvitatinVo;
import com.goparty.data.vo.FriendVo;
import com.goparty.webservice.FriendService;
import com.goparty.webservice.model.FriendRequest; 
import com.goparty.webservice.model.FriendInvitationRequest;
import com.goparty.webservice.model.GroupRequest;
import com.goparty.webservice.utils.ResponseUtil;


@Service("friendService")
@Transactional
public class FriendServiceImpl implements FriendService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FriendDataService friendDataService;
	
	@Autowired
	private UserDataService userDataService;
	
	@Override
	public Response invite(String token, String friendId,  FriendInvitationRequest request) {
		User user = userDataService.getUserByToken(token);	
		FriendInvitation invitation = new FriendInvitation();
		invitation.setInviterId(user.getId());
		invitation.setInviterMessage(request.getMessage());
		invitation.setInviteeId(friendId);
		invitation.setAcceptance(InvitationAcceptance.N);
		invitation.setStatus(InvitationStatus.INIT);
		invitation.setUpdateTime(new Date());			
		friendDataService.addInvitation(invitation);
		return ResponseUtil.buildResponse(invitation);
	}

	@Override
	public Response update(String token, String friendId, FriendRequest request) {
		User user = userDataService.getUserByToken(token);
		//user friend
		UserFriend uf = new UserFriend();
		uf.setUserId(user.getId());
		uf.setFriendId(friendId);
		uf.setRemarkName(request.getRemarkName());
		uf = friendDataService.update(uf);
		//update group
		if(!CollectionUtils.isEmpty(request.getGroups())){
			friendDataService.deleteUserFromGroup(friendId,user.getId());
			for(Group group: request.getGroups()){
				Group g = friendDataService.getGroup(group.getId());
				if(g==null || !g.getOwnerId().equals(user.getId())){
					throw new NullPointerException("The group you assigned is not belong to you.");
				}
				friendDataService.addUserToGroup(friendId, group.getId());
			}
		}
		
		return ResponseUtil.buildResponse(uf);
	}

	@Override
	public Response delete(String token, String friendId) {
		User user = userDataService.getUserByToken(token);
		UserFriendPK pk = new UserFriendPK();
		pk.setUserId(user.getId());
		pk.setFriendId(friendId);		
		return ResponseUtil.buildResponse(friendDataService.delete(pk));
	}
	
	@Override
	public Response getUnRespInvitations(String token,int offset,int limit) { 
		User user = userDataService.getUserByToken(token);		
		return ResponseUtil.buildResponse(friendDataService.getUnRespInvitations(user.getId(),offset,limit));
	}

	@Override
	public Response respondInvitation(String token, String invitationId, FriendInvitationRequest request) {
		User user = userDataService.getUserByToken(token);	
		//invitation
		FriendInvitation invitation = friendDataService.getInvitation(invitationId);
		if(!user.getId().equals(invitation.getInviteeId())){
			throw new BaseException("You have no permission to respond this invitation.");
		}
		
		if(request.getAcceptance().equals(InvitationAcceptance.Y.toString())){
			invitation.setAcceptance(InvitationAcceptance.Y);
		}else{
			invitation.setAcceptance(InvitationAcceptance.N);
		}
		invitation.setStatus(InvitationStatus.RESP);
		invitation.setInviteeMessage(request.getMessage());
		invitation.setUpdateTime(new Date());
		friendDataService.updateInvitation(invitation);
		
		//user friend
		if(invitation.getAcceptance().equals(InvitationAcceptance.Y)){	
			UserFriend uf = new UserFriend(); 
			uf.setUserId(user.getId());
			uf.setFriendId(invitation.getInviterId());	
			uf.setStatus(UserFriend.STATUS_NORMAL);
			friendDataService.create(uf);
			logger.info("add friend successfully.");
			
			//update group
			if(!CollectionUtils.isEmpty(request.getGroups())){  
				friendDataService.deleteUserFromGroup(invitation.getInviterId(),user.getId());
				for(Group group: request.getGroups()){
					Group g = friendDataService.getGroup(group.getId());
					if(g==null || !g.getOwnerId().equals(user.getId())){
						throw new BaseException("The group you assigned is not belong to you.");
					}
					friendDataService.addUserToGroup(invitation.getInviterId(),group.getId());
				}
			}
		}		
		
		return ResponseUtil.buildResponse(true);
	}
	
	@Override
	public Response getRespInvitations(String token, int offset,
			int limit) {
		User user = userDataService.getUserByToken(token);		
		return ResponseUtil.buildResponse(friendDataService.getRespInvitations(user.getId(),offset,limit));
	}

	@Override
	public Response addGroup(String token, GroupRequest request) {
		User user = userDataService.getUserByToken(token);			
		Group group = new Group();
		group.setName(request.getGroupName());
		group.setOwnerId(user.getId());
		group = friendDataService.addGroup(group );
		return ResponseUtil.buildResponse(group);
	}

	@Override
	public Response getGroups(String token) {
		User user = userDataService.getUserByToken(token);
		List<Group> groups = friendDataService.getGroupsByUserId(user.getId());
		return ResponseUtil.buildResponse(groups);
	}
	
	@Override
	public Response updateGroup(String token, String groupId,  GroupRequest request) {
		User user = userDataService.getUserByToken(token);	
		Group group = new Group();
		group.setId(groupId);
		group.setName(request.getGroupName());
		group.setOwnerId(user.getId());		
		group = friendDataService.updateGroup(group );
		return ResponseUtil.buildResponse(group);
	}

	@Override
	public Response deleteGroup(String token, String groupId) {
		boolean result = friendDataService.deleteGroup(groupId);
		return ResponseUtil.buildResponse(result);
	}

	@Override
	public Response getFriends(String token,int offset,int limit) {  
		User user = userDataService.getUserByToken(token);
		List<FriendVo> friends = friendDataService.getFriends(user.getId(),offset,limit);
	 
		return ResponseUtil.buildResponse(friends);
	}

	

	
	 
 
	
}
