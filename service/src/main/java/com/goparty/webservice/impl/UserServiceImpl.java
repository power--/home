package com.goparty.webservice.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goparty.biz.model.User;
import com.goparty.data.service.UserDataService;
import com.goparty.webservice.UserService;


@Service("userService")
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserDataService userDataService;

	@Override
	public User read(String id) {
		User ret = userDataService.read(id);
		if(ret==null){
			logger.error("NULLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
		}
		return ret;
	}

	@Override
	public User create(User user) {
		userDataService.create(user);
		return user;
	}

	@Override
	public User update(User user) {
		userDataService.update(user);
		return user;
	}

	@Override
	public boolean delete(String id) {
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

	public UserDataService getUserDataService() {
		return userDataService;
	}

	public void setUserDataService(UserDataService userDataService) {
		this.userDataService = userDataService;
	}
}
