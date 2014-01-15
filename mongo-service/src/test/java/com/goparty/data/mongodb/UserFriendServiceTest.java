package com.goparty.data.mongodb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goparty.biz.model.UserFriend;
import com.goparty.data.service.UserFriendDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/test.xml")
public class UserFriendServiceTest {
	
	@Autowired
	UserFriendDataService userFriendDataService;
	
	@Test
	public void add(){
		UserFriend uf = new UserFriend(); 
		uf.setUserId("1");
		uf.setFriendId("2");		
		userFriendDataService.create(uf);
	}

}
