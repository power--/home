package com.goparty.data.service;
 

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goparty.data.model.User;
 

public class UserDataServiceTest extends AbstractRepositoryTest {

	@Autowired
	private UserDataService userDataService;
	
	@PersistenceContext
	private EntityManager em;

	
	@Test
	public void testFindByNameAndPrice() {
		User user = new User();
		user.setNickName("ahuuu");
		user.setPassword("password");
		
		List<User> friends = new ArrayList<User>();
		User f1 = new User();
		f1.setNickName("f1122");
		f1.setPassword("f1password");
		User f2 = new User(); 
//		f2.setId("12");
//		f2.setNickName("f22");
//		f2.setPassword("f2password");
//		friends.add(f1);
//		friends.add(f2);
		friends.add(userDataService.read("12"));
		user.setFriends(friends);
		
		userDataService.create(user);
		
		List<User> list = userDataService.findByNickNameLike("ahu333", 0, 8);
		for(User o : list){
			System.out.println(o.getNickName() + " -- " + o.getPassword() );
		} 
	} 
	
	
}
