package com.goparty.data.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.*;
import com.goparty.data.repository.IUserDataRepository;
 

@Repository("userDataService")
@Transactional
public class UserDataService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IUserDataRepository userDataRepository;
	
	public List<User> findByNickNameLike(String nickName,int page,int size){		
		PageRequest pageable = new PageRequest(page, size);		 
		Page<User> users = userDataRepository.findByNickNameLike(nickName, pageable);
		return users.getContent();
	}
	
	
	public User create(User user){
		try{
			String id = UUID.randomUUID().toString();
			user.setId(id);
		 
			userDataRepository.save(user);
		}catch(Exception ex){
			logger.error("error happended to add a user",ex);
			throw ex;
		}
		
		return user;
	}
	
	public User update(User user){
		try{ 
			userDataRepository.save(user);
		}catch(Exception ex){
			logger.error("error happended to add a user",ex);
			throw ex;
		}
		
		return user;
	}
	
	public User read(String id){
		User user = userDataRepository.findOne(id);		
		return user;
	}
	
	public boolean delete(String id){
		boolean ret = false;
		
		try{
			userDataRepository.delete(id);
			ret = true;
		}catch(Exception ex){
			logger.error("del user error",ex);
			throw ex;
		}
		
		return ret;
	}
}
