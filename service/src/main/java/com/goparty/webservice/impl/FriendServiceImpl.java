package com.goparty.webservice.impl;
  
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service; 
import org.springframework.util.CollectionUtils;

import com.goparty.data.constant.InvitationAcceptance;
import com.goparty.data.constant.InvitationStatus;
import com.goparty.data.model.FriendInvitation;
import com.goparty.data.model.Group;
import com.goparty.data.model.User;
import com.goparty.data.model.UserFriend; 
import com.goparty.data.model.UserFriendPK;
import com.goparty.data.service.FriendDataService; 
import com.goparty.data.service.UserDataService;
import com.goparty.data.vo.FriendInvitatinVo;
import com.goparty.webservice.FriendService;
import com.goparty.webservice.model.FriendRequest;
import com.goparty.webservice.model.FriendResponse;
import com.goparty.webservice.model.FriendInvitationRequest;
import com.goparty.webservice.model.GroupRequest;


@Service("friendService")
public class FriendServiceImpl implements FriendService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FriendDataService friendDataService;
	
	@Autowired
	private UserDataService userDataService;
	
	@Override
	public FriendInvitation invite(String token, String friendId,  FriendInvitationRequest request) {
		User user = userDataService.getUserByToken(token);	
		FriendInvitation invitation = new FriendInvitation();
		invitation.setInviterId(user.getId());
		invitation.setInviterMessage(request.getMessage());
		invitation.setInviteeId(friendId);
		invitation.setAcceptance(InvitationAcceptance.N);
		invitation.setStatus(InvitationStatus.INIT);
		invitation.setUpdateTime(new Date());			
		friendDataService.addInvitation(invitation);
		return invitation;
	}

	@Override
	public UserFriend update(String token, String friendId, FriendRequest request) {
		User user = userDataService.getUserByToken(token);
		//user friend
		UserFriend uf = new UserFriend();
		uf.setUserId(user.getId());
		uf.setFriendId(friendId);
		uf.setRemarkName(request.getRemarkName());
		uf = friendDataService.update(uf);
		//update group
		if(!CollectionUtils.isEmpty(request.getGroups())){
			friendDataService.deleteUserFromAllGroup(user.getId());
			for(Group group: request.getGroups()){
				friendDataService.addUserToGroup(user.getId(), group.getId());
			}
		}
		
		return uf;
	}

	@Override
	public boolean delete(String token, String friendId) {
		User user = userDataService.getUserByToken(token);
		UserFriendPK pk = new UserFriendPK();
		pk.setUserId(user.getId());
		pk.setFriendId(friendId);		
		return friendDataService.delete(pk);
	}
	
	@Override
	public List<FriendInvitatinVo> getUnRespInvitations(String token,int offset,int limit) { 
		User user = userDataService.getUserByToken(token);		
		return friendDataService.getUnRespInvitations(user.getId(),offset,limit);
	}

	@Override
	public boolean respondInvitation(String token, String invitationId, FriendInvitationRequest request) {
		//invitation
		FriendInvitation invitation = friendDataService.getInvitation(invitationId);
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
			User user = userDataService.getUserByToken(token);		
			UserFriend uf = new UserFriend(); 
			uf.setUserId(user.getId());
			uf.setFriendId(invitation.getInviteeId());	
			uf.setStatus(UserFriend.STATUS_NORMAL);
			friendDataService.create(uf);
			logger.info("add friend successfully.");
		}		
		return true;
	}
	
	@Override
	public List<FriendInvitatinVo> getRespInvitations(String token, int offset,
			int limit) {
		User user = userDataService.getUserByToken(token);		
		return friendDataService.getRespInvitations(user.getId(),offset,limit);
	}

	@Override
	public Group addGroup(String token, GroupRequest request) {
		User user = userDataService.getUserByToken(token);			
		Group group = new Group();
		group.setName(request.getGroupName());
		group.setOwnerId(user.getId());
		group = friendDataService.addGroup(group );
		return group;
	}

	@Override
	public Group updateGroup(String token, String groupId,  GroupRequest request) {
		User user = userDataService.getUserByToken(token);	
		Group group = new Group();
		group.setId(groupId);
		group.setName(request.getGroupName());
		group.setOwnerId(user.getId());		
		group = friendDataService.updateGroup(group );
		return group;
	}

	@Override
	public boolean deleteGroup(String token, String groupId) {
		boolean result = friendDataService.deleteGroup(groupId);
		return result;
	}

	@Override
	public List<FriendResponse> getFriends(String token,int offset,int limit) {
		List<FriendResponse> list = new ArrayList<FriendResponse>();
		PageRequest pageable = new PageRequest(offset, limit);	
		User user = userDataService.getUserByToken(token);
		List<UserFriend> friends = friendDataService.getFriends(user.getId(),pageable);
		for(UserFriend friend : friends){
			FriendResponse fr = new FriendResponse();
			fr.setFriendId(friend.getFriendId());
			fr.setRemarkName(friend.getRemarkName());
			fr.setGroups(friend.getGroups());
			list.add(fr);
		} 
		return list;
	}

	

	
	 
 
	
}
