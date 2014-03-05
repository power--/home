package com.goparty.webservice.impl;
  
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service; 

import com.goparty.data.model.Group;
import com.goparty.data.model.User;
import com.goparty.data.model.UserFriend; 
import com.goparty.data.model.UserFriendPK;
import com.goparty.data.service.FriendDataService; 
import com.goparty.data.service.UserDataService;
import com.goparty.webservice.FriendService;
import com.goparty.webservice.model.FriendRequest;
import com.goparty.webservice.model.InvitationRequest;


@Service("friendService")
public class FriendServiceImpl implements FriendService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FriendDataService friendDataService;
	
	@Autowired
	private UserDataService userDataService;
	
	@Override
	public boolean add(String token, String friendId,  FriendRequest request) {
		User user = userDataService.getUserByToken(token);		
		UserFriend uf = new UserFriend();
		uf.setUserId(user.getId());
		uf.setFriendId(friendId);	 
		
		List<Group> groups = new ArrayList<Group>();
		Group g = new Group();
		g.setId(request.getGroupId());
		groups.add(g);
		uf.setGroups(groups);
		friendDataService.create(uf);
		return true;
	}

	@Override
	public UserFriend update(String token, String friendId, FriendRequest request) {
		User user = userDataService.getUserByToken(token);
		UserFriend uf = new UserFriend();
		uf.setUserId(user.getId());
		uf.setFriendId(friendId);
		uf.setRemarkName(request.getRemarkName());
		uf = friendDataService.update(uf);
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
	public List<UserFriend> getFriendInvitationList(String token,int offset,int limit) {
		PageRequest pageable = new PageRequest(offset, limit);		 
		User user = userDataService.getUserByToken(token);		
		return friendDataService.getFriendInvitationList(user.getId(),pageable);
	}

	@Override
	public boolean respondFriendInvitation(String token, String friendId, InvitationRequest request) {
		User user = userDataService.getUserByToken(token);		
		UserFriend uf = new UserFriend(); 
		uf.setUserId(user.getId());
		uf.setFriendId(friendId);	
		uf.setStatus(request.getResponse());
		friendDataService.update(uf);
		return true;
	}

	@Override
	public Group addGroup(String token, FriendRequest request) {
		User user = userDataService.getUserByToken(token);	
		
		Group group = new Group();
		group.setName(request.getGroupName());
		group.setOwnerId(user.getId());
		group = friendDataService.addGroup(group );
		return group;
	}

	@Override
	public Group updateGroup(String token, String groupId,  FriendRequest request) {
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

	
	 
 
	
}
