package com.goparty.data.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional; 

import com.goparty.data.exception.BaseException;
import com.goparty.data.model.FriendInvitation;
import com.goparty.data.model.Group;
import com.goparty.data.model.UserFriend;
import com.goparty.data.model.UserFriendPK;
import com.goparty.data.repository.IFriendDataRepository;
import com.goparty.data.repository.IFriendInvitationDataRepository;
import com.goparty.data.repository.IGroupDataRepository;
import com.goparty.data.repository.IUserDataRepository;
import com.goparty.data.vo.FriendInvitatinVo;
@Repository("friendDataService")
@Transactional
public class FriendDataService {
	
	private Log log = LogFactory.getLog(FriendDataService.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private IFriendInvitationDataRepository friendInvitationDataRepository;
	
	@Autowired
	private IFriendDataRepository friendDataRepository;
	
	@Autowired
	private IGroupDataRepository groupDataRepository;
	
	@Autowired
	private IUserDataRepository userDataRepository;
	
	//invitation
	public void invite(FriendInvitation invitation){
		friendInvitationDataRepository.save(invitation);
	}
	
	//user friend
	public UserFriend read(UserFriendPK id) {
		UserFriend friend = friendDataRepository.findOne(id);
		return friend;
	}

	public UserFriend create(UserFriend uf) { 
		List<Group> groups = uf.getGroups();
		if(groups!=null){
			for(Group g : groups){
				addUserToGroup(uf.getFriendId(), g.getId());
			}
		}
		uf.setUpdateTime(new Date());
		uf.setStatus(UserFriend.STATUS_INIT);
		uf = friendDataRepository.save(uf);
		return uf;
	}

	public UserFriend update(UserFriend userFriend) {
		UserFriendPK pk = new UserFriendPK();
		pk.setUserId(userFriend.getUserId());
		pk.setFriendId(userFriend.getFriendId());
		UserFriend uf = friendDataRepository.findOne(pk);
		if(uf == null){
			throw new BaseException("Friend not exist");
		}
		
		if(userFriend.getStatus()!=null){
			uf.setStatus(userFriend.getStatus());	
		}
		if(userFriend.getRemarkName()!=null){
			uf.setRemarkName(userFriend.getRemarkName());
		}
		uf.setUpdateTime(new Date());		
		return friendDataRepository.save(uf);
		
	}

	public boolean delete(UserFriendPK id) {
		boolean ret = false;		
		try{
			friendDataRepository.delete(id);
			ret = true;
		}catch(Exception ex){
			log.error("del user error",ex);
			throw ex;
		}		
		return ret;
	}
	
	public List<FriendInvitatinVo> getUnRespInvitations(String userId, int offset, int limit) { 
		Query  query = em.createNativeQuery("SELECT i.id as invitationId,i.inviterMessage,i.`status`,i.updateTime,u.id as userId,u.nickName,u.birthdate,u.gender,u.location,u.signature,u.photo"
				+ " FROM gp_friend_invitation i join gp_user u on i.inviterId=u.id where  i.`status`='INIT' and i.inviteeId=:userId limit :offset, :limit", FriendInvitatinVo.class);
		query.setParameter("userId", userId);
		query.setParameter("offset", offset);
		query.setParameter("limit", limit);
		return query.getResultList();
	}
	
	public List<UserFriend> getFriends(String userId, Pageable pageable) {		 
		List<UserFriend> friends = friendDataRepository.findByUserIdAndStatus(userId, UserFriend.STATUS_AGREE, pageable);
		for(UserFriend f : friends){		
			f.setGroups(this.getGroups(f.getFriendId()));
		}
		return friends;
	}
	
	
	//group
	public Group addGroup(Group group){
		if(StringUtils.isEmpty(group.getName())){
			throw new BaseException("group name should not be empty.");
		}
		return groupDataRepository.save(group);
	}
	
	public Group updateGroup(Group group){
		Group g = groupDataRepository.findOne(group.getId());
		if(g!=null){
			g.setName(group.getName());
			g = groupDataRepository.save(g);
		}
		return g;
	}
	
	public boolean deleteGroup(String id){
		boolean ret = false;		
		try{
			groupDataRepository.delete(id);
			ret = true;
		}catch(Exception ex){
			log.error("del group error",ex);
			throw ex;
		}		
		return ret;
	}
	
	
	public boolean addUserToGroup(String userId,String groupId){
		Query query = em.createNativeQuery("insert into gp_group_user(groupId,userId) values(?,?)");
		query.setParameter(1, groupId);
		query.setParameter(2, userId);
		query.executeUpdate();
		return true;
	}
	
	public List<Group> getGroups(String userId){
		Query  query = em.createNativeQuery("select g.* from gp_group_user gu join gp_group g on gu.groupId=g.id where gu.userId=92 ", Group.class);
		return query.getResultList();
		
	}


	
}
