package com.goparty.data.dao;

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
import com.goparty.data.vo.FriendVo;
//@Repository("friendDataService")
@Transactional
public class FriendDao {
	
	private Log log = LogFactory.getLog(FriendDao.class);
	
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

	private String sqlGetFriends;
	
	
	
	public String getSqlGetFriends() {
		return sqlGetFriends;
	}
	public void setSqlGetFriends(String sqlGetFriends) {
		this.sqlGetFriends = sqlGetFriends;
	}
	//invitation
	public void addInvitation(FriendInvitation invitation){
		friendInvitationDataRepository.save(invitation);
	}
	public FriendInvitation getInvitation(String invitationId){
		return friendInvitationDataRepository.findOne(invitationId);
	}
	public void updateInvitation(FriendInvitation invitation){
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
		uf.setStatus(UserFriend.STATUS_NORMAL);
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
	
	public List<FriendInvitatinVo> getUnRespInvitations(String inviteeId, int offset, int limit) { 
		Query  query = em.createNativeQuery("SELECT i.id as invitationId,i.inviterMessage as message,i.`status`,i.updateTime,u.id as userId,u.nickName,u.birthdate,u.gender,u.location,u.signature,u.photo"
				+ " FROM gp_friend_invitation i join gp_user u on i.inviterId=u.id where  i.`status`='INIT' and i.inviteeId=:inviteeId limit :offset, :limit", FriendInvitatinVo.class);
		query.setParameter("inviteeId", inviteeId);
		query.setParameter("offset", offset);
		query.setParameter("limit", limit);
		return query.getResultList();
	}
	
	public List<FriendInvitatinVo> getRespInvitations(String inviterId, int offset, int limit) { 
		Query  query = em.createNativeQuery("SELECT i.id as invitationId,i.inviteeMessage as message,i.`status`,i.updateTime,u.id as userId,u.nickName,u.birthdate,u.gender,u.location,u.signature,u.photo"
				+ " FROM gp_friend_invitation i join gp_user u on i.inviteeId=u.id where  i.`status`='RESP' and i.inviterId=:inviterId limit :offset, :limit", FriendInvitatinVo.class);
		query.setParameter("inviterId", inviterId);
		query.setParameter("offset", offset);
		query.setParameter("limit", limit);
		return query.getResultList();
	}
	
	
	public List<FriendVo> getFriends(String userId, int offset, int limit) {		 
		Query  query = em.createNativeQuery(sqlGetFriends, FriendVo.class);
		query.setParameter("userId", userId);
		query.setParameter("offset", offset);
		query.setParameter("limit", limit); 
		List<FriendVo> friends = query.getResultList();
		for(FriendVo f : friends){		
			f.setGroups(this.getGroups(userId,f.getId()));
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
	
	public List<Group> getGroupsByUserId(String userId){
		return groupDataRepository.findByOwnerId(userId);
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
	
	public Group getGroup(String id){
		return groupDataRepository.findOne(id);
	}
	
	public void deleteUserFromGroup(String userId, String groupOwnerId){ 
		Query query = em.createNativeQuery("delete gu from gp_group_user gu join gp_group g on g.id=gu.groupId where userId = :userId and g.ownerId=:groupOwnerId");
		query.setParameter("userId", userId);
		query.setParameter("groupOwnerId", groupOwnerId);
		query.executeUpdate();
	}
	public void addUserToGroup(String userId,String groupId){
		Query query = em.createNativeQuery("insert into gp_group_user(groupId,userId) values(?,?)");
		query.setParameter(1, groupId);
		query.setParameter(2, userId);
		query.executeUpdate(); 
	}
	
	public List<Group> getGroups(String userId,String friendId){
		Query  query = em.createNativeQuery("select g.* from gp_group_user gu join gp_group g on gu.groupId=g.id where gu.userId=:friendId and g.ownerId=:userId", Group.class);
		query.setParameter("userId", userId);
		query.setParameter("friendId", friendId);
		return query.getResultList();
		
	}

	

	
}
