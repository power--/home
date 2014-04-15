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
import com.goparty.data.dao.FriendDao;
import com.goparty.data.dao.UserDao;
import com.goparty.data.exception.BaseException;
import com.goparty.data.model.FriendInvitation;
import com.goparty.data.model.Group;
import com.goparty.data.model.User;
import com.goparty.data.model.UserFriend; 
import com.goparty.data.model.UserFriendPK;
import com.goparty.data.vo.FriendInvitatinVo;
import com.goparty.data.vo.FriendVo;
import com.goparty.webservice.FriendService;
import com.goparty.webservice.model.FriendRequest; 
import com.goparty.webservice.model.FriendInvitationRequest;
import com.goparty.webservice.model.GroupRequest;
import com.goparty.webservice.model.InvitationResponse;
import com.goparty.webservice.model.UserResponse;
import com.goparty.webservice.utils.ResponseUtil;


@Service("friendService")
@Transactional
public class FriendServiceImpl implements FriendService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FriendDao friendDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public Response invite(String token, String friendId,  FriendInvitationRequest request) {
		User user = userDao.getUserByToken(token);	
		FriendInvitation invitation = new FriendInvitation();
		invitation.setInviterId(user.getId());
		invitation.setInviterMessage(request.getMessage());
		invitation.setInviteeId(friendId);
		invitation.setAcceptance(InvitationAcceptance.N);
		invitation.setStatus(InvitationStatus.INIT);
		invitation.setUpdateTime(new Date());			
		friendDao.addInvitation(invitation);
		return ResponseUtil.buildResponse(invitation);
	}

	@Override
	public Response update(String token, String friendId, FriendRequest request) {
		User user = userDao.getUserByToken(token);
		//user friend
		UserFriend uf = new UserFriend();
		uf.setUserId(user.getId());
		uf.setFriendId(friendId);
		uf.setRemarkName(request.getRemarkName());
		uf = friendDao.update(uf);
		//update group
		if(!CollectionUtils.isEmpty(request.getGroups())){
			friendDao.deleteUserFromGroup(friendId,user.getId());
			for(Group group: request.getGroups()){
				Group g = friendDao.getGroup(group.getId());
				if(g==null || !g.getOwnerId().equals(user.getId())){
					throw new NullPointerException("The group you assigned is not belong to you.");
				}
				friendDao.addUserToGroup(friendId, group.getId());
			}
		}
		
		return ResponseUtil.buildResponse(uf);
	}

	@Override
	public Response delete(String token, String friendId) {
		User user = userDao.getUserByToken(token);
		UserFriendPK pk = new UserFriendPK();
		pk.setUserId(user.getId());
		pk.setFriendId(friendId);		
		return ResponseUtil.buildResponse(friendDao.delete(pk));
	}
	
	@Override
	public Response getUnRespInvitations(String token,int offset,int limit) { 
		User user = userDao.getUserByToken(token);		
		List<FriendInvitatinVo> friendInvitatinVos = friendDao.getUnRespInvitations(user.getId(),offset,limit);		
		List<InvitationResponse> invitations= new ArrayList<InvitationResponse>();
		for(FriendInvitatinVo vo : friendInvitatinVos){
			InvitationResponse invitation = new InvitationResponse();
			invitation.setInvitationId(vo.getInvitationId());
			UserResponse inviter = new UserResponse();
			inviter.setId(vo.getUserId());
			inviter.setLocation(vo.getLocation());
			inviter.setNickName(vo.getNickName());
			inviter.setPhoto(vo.getPhoto());
			inviter.setSignature(vo.getSignature());
			inviter.setBirthdate(vo.getBirthdate());
			inviter.setGender(vo.getGender());
			inviter.setPhone(vo.getPhoto());
			inviter.setWeChat(vo.getWeChat());
			inviter.setWeibo(vo.getWeibo());
			inviter.setQq(vo.getQq()); 
			invitation.setInviter(inviter );
			invitation.setInviterMessage(vo.getMessage());;
			invitation.setStatus(vo.getStatus());
			invitation.setUpdateTime(vo.getUpdateTime());
			invitations.add(invitation);
		}
		return ResponseUtil.buildResponse(invitations);
	}

	@Override
	public Response respondInvitation(String token, String invitationId, FriendInvitationRequest request) {
		User user = userDao.getUserByToken(token);	
		//invitation
		FriendInvitation invitation = friendDao.getInvitation(invitationId);
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
		friendDao.updateInvitation(invitation);
		
		//user friend
		if(invitation.getAcceptance().equals(InvitationAcceptance.Y)){	
			UserFriend uf = new UserFriend(); 
			uf.setUserId(user.getId());
			uf.setFriendId(invitation.getInviterId());	
			uf.setStatus(UserFriend.STATUS_NORMAL);
			friendDao.create(uf);
			
			UserFriend uf2 = new UserFriend(); 
			uf2.setUserId(invitation.getInviterId());
			uf2.setFriendId(user.getId());	
			uf2.setStatus(UserFriend.STATUS_NORMAL);
			friendDao.create(uf2);
			logger.info("add friend successfully.");
			
			//update group
			if(!CollectionUtils.isEmpty(request.getGroups())){  
				friendDao.deleteUserFromGroup(invitation.getInviterId(),user.getId());
				for(Group group: request.getGroups()){
					Group g = friendDao.getGroup(group.getId());
					if(g==null || !g.getOwnerId().equals(user.getId())){
						throw new BaseException("The group you assigned is not belong to you.");
					}
					friendDao.addUserToGroup(invitation.getInviterId(),group.getId());
				}
			}
		}		
		
		return ResponseUtil.buildResponse(true);
	}
	
	@Override
	public Response getRespInvitations(String token, int offset,int limit) {
		User user = userDao.getUserByToken(token);				
		List<FriendInvitatinVo> friendInvitatinVos = friendDao.getRespInvitations(user.getId(),offset,limit);		
		List<InvitationResponse> invitations= new ArrayList<InvitationResponse>();
		for(FriendInvitatinVo vo : friendInvitatinVos){
			InvitationResponse invitation = new InvitationResponse();
			invitation.setInvitationId(vo.getInvitationId());
			UserResponse invitee = new UserResponse();
			invitee.setId(vo.getUserId());
			invitee.setLocation(vo.getLocation());
			invitee.setNickName(vo.getNickName());
			invitee.setPhoto(vo.getPhoto());
			invitee.setSignature(vo.getSignature());
			invitee.setBirthdate(vo.getBirthdate());
			invitee.setGender(vo.getGender());
			invitee.setPhone(vo.getPhoto());
			invitee.setWeChat(vo.getWeChat());
			invitee.setWeibo(vo.getWeibo());
			invitee.setQq(vo.getQq()); 
			invitation.setInviter(invitee );
			invitation.setInviteeMessage(vo.getMessage());;
			invitation.setStatus(vo.getStatus());
			invitation.setUpdateTime(vo.getUpdateTime());	
			invitations.add(invitation);
		}
		return ResponseUtil.buildResponse(invitations); 
	}

	@Override
	public Response addGroup(String token, GroupRequest request) {
		User user = userDao.getUserByToken(token);			
		Group group = new Group();
		group.setName(request.getGroupName());
		group.setOwnerId(user.getId());
		group = friendDao.addGroup(group );
		return ResponseUtil.buildResponse(group);
	}

	@Override
	public Response getGroups(String token) {
		User user = userDao.getUserByToken(token);
		List<Group> groups = friendDao.getGroupsByUserId(user.getId());
		return ResponseUtil.buildResponse(groups);
	}
	
	@Override
	public Response updateGroup(String token, String groupId,  GroupRequest request) {
		User user = userDao.getUserByToken(token);	
		Group group = new Group();
		group.setId(groupId);
		group.setName(request.getGroupName());
		group.setOwnerId(user.getId());		
		group = friendDao.updateGroup(group );
		return ResponseUtil.buildResponse(group);
	}

	@Override
	public Response deleteGroup(String token, String groupId) {
		boolean result = friendDao.deleteGroup(groupId);
		return ResponseUtil.buildResponse(result);
	}

	@Override
	public Response getFriends(String token,int offset,int limit) {  
		User user = userDao.getUserByToken(token);
		List<FriendVo> friends = friendDao.getFriends(user.getId(),offset,limit);
	 
		return ResponseUtil.buildResponse(friends);
	}

	

	
	 
 
	
}
