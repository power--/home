package com.goparty.data.service;
 

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goparty.data.model.Event;
import com.goparty.data.model.UserProfile;
import com.goparty.data.model.UserToken;
 

public class UserDataServiceTest extends AbstractRepositoryTest {

	@Autowired
	private UserDataService userDataService;
	
	@PersistenceContext
	private EntityManager em;

	
	@Test
	public void testToken(){
		UserToken token = userDataService.generateToken("33",30);
		UserProfile u = userDataService.getUserByToken(token.getToken());
		assertTrue(u!=null);
	}
	
	@Test
	public void testSearch(){
		List<UserProfile> list = userDataService.search("%ahu%", 0, 10);
		System.out.println(list.size());
	}
	
	@Test
	public void testEm(){
//		TypedQuery<User> query = em.createQuery("select u from User u where userName='ahu'", User.class);
		 Query query = em.createNativeQuery("select * from gp_user where loginId='ahu'", UserProfile.class);
		List<UserProfile> list = query.getResultList();
		for(UserProfile u : list){
			System.out.println(u.getNickName() + " -- " + u.getPassword() );
		}
	}
	
	@Test
	public void testCreate() {
		UserProfile user = new UserProfile();
		user.setLoginId("ahub");
		user.setNickName("ahuuu");
		user.setPassword("password");
		
		List<UserProfile> friends = new ArrayList<UserProfile>();
		UserProfile f1 = new UserProfile();
		f1.setNickName("f1122");
		f1.setPassword("f1password");
		UserProfile f2 = new UserProfile(); 
//		f2.setId("12");
//		f2.setNickName("f22");
//		f2.setPassword("f2password");
//		friends.add(f1);
//		friends.add(f2);
		UserProfile u33 = userDataService.read("33");
		for(UserProfile friend : u33.getFriends()){
			System.out.println(friend.getId() + " , Name = " + friend.getLoginId());
		}
		friends.add(u33);
		user.setFriends(friends);
		
		userDataService.create(user);
		user.setNickName("ahu-new");
		userDataService.update(user);
		
//		
//		List<User> list = userDataService.findByNickNameLike("ahu333", 0, 8);
//		for(User o : list){
//			System.out.println(o.getNickName() + " -- " + o.getPassword() );
//		} 
	} 
	 
	@Test
	public void testPage(){
		List<Event> list = userDataService.findByEventCategoryId("1", 1, 5);
		for(Event o : list){			
			System.out.println(o.getDescription() + "--" + o.getOwner().getId());
			for(UserProfile u : o.getAttendees()){
				System.out.println("user name:" + u.getLoginId());
			}
			
		}
	}
	
}
