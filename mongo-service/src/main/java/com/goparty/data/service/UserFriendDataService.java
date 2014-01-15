package com.goparty.data.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 

import com.goparty.biz.model.UserFriend;
import com.goparty.data.model.UserFriendData; 
import com.goparty.repository.IUserFriendDataRepository;

@Repository("userFriendDataService")
@Transactional
public class UserFriendDataService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IUserFriendDataRepository userFriendDataRepository;
	
	public UserFriend create(UserFriend uf){
		try{
			String id = UUID.randomUUID().toString();
			
			UserFriendData data = new UserFriendData();
			data.setId(id);
			data.setUserId(uf.getUserId());
			data.setFriendId(uf.getFriendId());			
			userFriendDataRepository.save(data);
		}catch(Exception ex){
			logger.error("error happended to add a userFriend",ex);
			throw ex;
		}
		
		return uf;
	}
	
	public UserFriend update(UserFriend uf){
		try{
			UserFriendData data = new UserFriendData();
			
			
			userFriendDataRepository.save(data);
		}catch(Exception ex){
			logger.error("error happended to update a userFriend",ex);
			throw ex;
		}
		
		return uf;
	}
	
	public UserFriend read(String id){ 
		UserFriendData data = userFriendDataRepository.findOne(id);
		UserFriend uf = new UserFriend();
		
		return uf;
	}
	
	public boolean delete(String id){
		boolean ret = false;
		
		try{
			userFriendDataRepository.delete(id);
			ret = true;
		}catch(Exception ex){
			logger.error("del userFriend error",ex);
			throw ex;
		}
		
		return ret;
	}
}
