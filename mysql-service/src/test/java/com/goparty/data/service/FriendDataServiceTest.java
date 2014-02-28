package com.goparty.data.service;
 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goparty.data.model.Event;
import com.goparty.data.model.User;
import com.goparty.data.model.UserFriend;
import com.goparty.data.model.UserFriendPK;
 

public class FriendDataServiceTest extends AbstractRepositoryTest {

	@Autowired
	private FriendDataService friendDataService;
	 

	@Test
	public void testCreate(){
		UserFriend uf = new UserFriend();
		uf.setUserId("22");
		uf.setFriendId("92");
		uf.setStatus("INIT");
		uf.setUpdateTime(new Date());
		friendDataService.create(uf);
		
		uf.setStatus("AGREE");
		friendDataService.update(uf);
	}
 
	@Test
	public void testDelete(){
		UserFriendPK pk = new UserFriendPK();
		pk.setUserId("22");
		pk.setFriendId("92");
		friendDataService.delete(pk);
		
	}
	 
 
	
}

