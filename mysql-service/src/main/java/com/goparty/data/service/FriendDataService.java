package com.goparty.data.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.UserFriend;
import com.goparty.data.model.UserFriendPK;
import com.goparty.data.repository.IFriendDataRepository;
@Repository("friendDataService")
@Transactional
public class FriendDataService {
	
	private Log log = LogFactory.getLog(FriendDataService.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private IFriendDataRepository friendDataRepository;
	
	
	public UserFriend read(UserFriendPK id) {
		UserFriend friend = friendDataRepository.findOne(id);
		return friend;
	}

	public UserFriend create(UserFriend friend) {
		return friendDataRepository.save(friend);
	}

	public UserFriend update(UserFriend userFriend) {
		UserFriendPK pk = new UserFriendPK();
		pk.setUserId(userFriend.getUserId());
		pk.setFriendId(userFriend.getFriendId());
		UserFriend uf = friendDataRepository.findOne(pk);
		
		if(userFriend.getStatus()!=null){
			uf.setStatus(userFriend.getStatus());	
		}
		if(userFriend.getRemarkName()!=null){
			uf.setRemarkName(userFriend.getRemarkName());
		}
		
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
	
	public List<UserFriend> getFriendInvitationList(String userId) {
		return friendDataRepository.findByUserIdAndStatus(userId, "INIT");
	}

}
