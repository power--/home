package com.goparty.webservice.impl;
  
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import com.goparty.data.model.User;
import com.goparty.data.model.UserFriend; 
import com.goparty.data.model.UserFriendPK;
import com.goparty.data.service.FriendDataService; 
import com.goparty.data.service.UserDataService;
import com.goparty.webservice.FriendService;
import com.goparty.webservice.model.InvitationRequest;


@Service("friendService")
public class FriendServiceImpl implements FriendService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FriendDataService friendDataService;
	
	@Autowired
	private UserDataService userDataService;
	
	@Override
	public boolean add(String token, String friendId) {
		User user = userDataService.getUserByToken(token);		
		UserFriend uf = new UserFriend();
		uf.setUserId(user.getId());
		uf.setFriendId(friendId);		
		friendDataService.create(uf);
		return true;
	}

	@Override
	public UserFriend update(String token, UserFriend uf) {
		User user = userDataService.getUserByToken(token);
		uf.setUserId(user.getId());
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
	public List<UserFriend> getFriendInvitationList(String token) {
		User user = userDataService.getUserByToken(token);		
		return friendDataService.getFriendInvitationList(user.getId());
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

	
	 
 
	
}
