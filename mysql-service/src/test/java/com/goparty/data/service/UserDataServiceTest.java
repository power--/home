package com.goparty.data.service;
 

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
		user.setNickName("ahu");
		user.setPassword("password");
		userDataService.create(user);
		
		List<User> list = userDataService.findByNickNameLike("a", 0, 1);
		for(User o : list){
			System.out.println(o.getPassword());
		} 
	}
 
	
	
}
