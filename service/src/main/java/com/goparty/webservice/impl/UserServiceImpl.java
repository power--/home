package com.goparty.webservice.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goparty.biz.model.User;
import com.goparty.webservice.UserService;
import com.party.data.service.UserDataService;


@Service("userService")
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserDataService userDataService;

	@Override
	public User getUser(String id) {
		User ret = userDataService.read(id);
		return ret;
	}

	@Override
	public User addUser(User user) {
		userDataService.create(user);
		return user;
	}

	@Override
	public User updateUser(User user) {
		userDataService.update(user);
		return user;
	}

	@Override
	public boolean deleteUser(String id) {
		boolean ret = false;
		try{
			userDataService.delete(id);
			ret = true;
		}catch(Exception ex){
			logger.error("del user error",ex);
			throw ex;
		}
		
		return ret;
	}

}
