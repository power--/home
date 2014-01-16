package com.goparty.data.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.biz.model.User;
import com.goparty.data.model.UserData;
import com.goparty.repository.IUserDataRepository;

@Repository("userDataService")
@Transactional
public class UserDataService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IUserDataRepository userDataRepository;
	
	public List<UserData> getUserDataByNickName(String nickName){
		return userDataRepository.getUserDataByNickName(nickName);
	}
	
	public User create(User user){
		try{
			String id = UUID.randomUUID().toString();
			user.setId(id);
			
			UserData data = new UserData();
			
			data.setId(user.getId());
			data.setUserName(user.getUserName());
			data.setPassword(user.getPassword());
			data.setNickName(user.getNickName());
			data.setPhone(user.getPhone());
			data.setBirthdate(user.getBirthdate());
			data.setGender(user.getGender());
			data.setLocation(user.getLocation());
			data.setSignature(user.getSignature());
			data.setPhoto(user.getPhoto());
			data.setWeChat(user.getWeChat());
			data.setQQ(user.getQQ());
			
			userDataRepository.save(data);
		}catch(Exception ex){
			logger.error("error happended to add a user",ex);
			throw ex;
		}
		
		return user;
	}
	
	public User update(User user){
		try{
			UserData data = new UserData();
			
			data.setId(user.getId());
			data.setUserName(user.getUserName());
			data.setPassword(user.getPassword());
			data.setNickName(user.getNickName());
			data.setPhone(user.getPhone());
			data.setBirthdate(user.getBirthdate());
			data.setGender(user.getGender());
			data.setLocation(user.getLocation());
			data.setSignature(user.getSignature());
			data.setPhoto(user.getPhoto());
			data.setWeChat(user.getWeChat());
			data.setQQ(user.getQQ());
			
			userDataRepository.save(data);
		}catch(Exception ex){
			logger.error("error happended to add a user",ex);
			throw ex;
		}
		
		return user;
	}
	
	public User read(String id){
		User user = null;
		
		UserData data = userDataRepository.findOne(id);
		
		if(data != null){
			user = new User();
			user.setId(data.getId());
			user.setUserName(data.getUserName());
			user.setPassword(data.getPassword());
			user.setNickName(data.getNickName());
			user.setPhone(data.getPhone());
			user.setBirthdate(data.getBirthdate());
			user.setGender(data.getGender());
			user.setLocation(data.getLocation());
			user.setSignature(data.getSignature());
			user.setPhoto(data.getPhoto());
			user.setWeChat(data.getWeChat());
			user.setQQ(data.getQQ());
		}
		
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
