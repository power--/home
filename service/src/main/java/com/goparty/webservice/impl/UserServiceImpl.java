package com.goparty.webservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goparty.model.User;
import com.goparty.webservice.UserService;
import com.party.data.service.UserDataService;


@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDataService userDataService;

	@Override
	public User getUser(String id) {
		User ret = new User();
		ret.setId(id);
		return ret;
	}

	@Override
	public User addUser(User user) {
		user.setId(java.util.UUID.randomUUID().toString());
		return user;
	}

	@Override
	public User updateUser(User user) {
		return user;
	}

	@Override
	public boolean deleteUser(String id) {
		return true;
	}

}
