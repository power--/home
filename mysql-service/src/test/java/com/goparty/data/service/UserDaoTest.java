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

import com.goparty.data.dao.UserDao;
import com.goparty.data.model.Event;
import com.goparty.data.model.User;
import com.goparty.data.model.UserToken;
 

public class UserDaoTest extends AbstractRepositoryTest {

	@Autowired
	private UserDao userDao;
	
	@PersistenceContext
	private EntityManager em;

	
	@Test
	public void testToken(){
		UserToken token = userDao.generateToken("33",30);
		User u = userDao.getUserByToken(token.getToken());
		assertTrue(u!=null);
	}
	
	@Test
	public void testSearch(){
		List<User> list = userDao.search("%ahu%", 0, 10);
		System.out.println(list.size());
	}
	
	@Test
	public void testEm(){
//		TypedQuery<User> query = em.createQuery("select u from User u where userName='ahu'", User.class);
		 Query query = em.createNativeQuery("select * from gp_user where loginId='ahu'", User.class);
		List<User> list = query.getResultList();
		for(User u : list){
			System.out.println(u.getNickName() + " -- " + u.getPassword() );
		}
	}
	
	@Test
	public void testCreate() {
		User user = new User();
		user.setLoginId("ahub");
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
//		User u33 = userDao.read("33");
//		for(User friend : u33.getFriends()){
//			System.out.println(friend.getId() + " , Name = " + friend.getLoginId());
//		}
//		friends.add(u33);
//		user.setFriends(friends);
		
		userDao.create(user);
		user.setNickName("ahu-new");
		userDao.update(user);
		
//		
//		List<User> list = userDataService.findByNickNameLike("ahu333", 0, 8);
//		for(User o : list){
//			System.out.println(o.getNickName() + " -- " + o.getPassword() );
//		} 
	} 
	 
	@Test
	public void testPage(){
		List<Event> list = userDao.findByOwnerId("1", 1, 5);
		for(Event o : list){			
			System.out.println(o.getDescription() + "--" + o.getOwner().getId());
			for(User u : o.getMembers()){
				System.out.println("user name:" + u.getLoginId());
			}
			
		}
	}
	
}
