package com.goparty.data.service;
 

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goparty.data.constant.EventStatus;
import com.goparty.data.constant.EventVisibility;
import com.goparty.data.dao.EventDao;
import com.goparty.data.model.Event;
import com.goparty.data.model.Category;
import com.goparty.data.model.User;
 

public class EventDaoTest extends AbstractRepositoryTest {

	@Autowired
	private EventDao eventDao;
	 

	
//	@Test
	public void test() {
		Event event = new Event(); 
		event.setDescription("Hello World");
		event.setTitle("A Title");
		event.setEventStatus(EventStatus.INIT);
		
		User owner = new User();
		owner.setId("21");
		event.setOwner(owner);
		
		List<User> attendees = new ArrayList<User>();
		User user1 = new User();
		user1.setId("18");  
		User user2 = new User();
		user2.setId("19");  

		attendees.add(owner);
		attendees.add(user1);
		attendees.add(user2);		
		event.setAttendees(attendees); 
		
		Category cate = new Category();
		cate.setId("1");
		event.setEventCategory(cate);		
		event = eventDao.create(event);
		
		Event e = eventDao.read(event.getId()); 
		assertEquals("Hello World",e.getDescription());
		assertNotNull(e.getOwner().getNickName());
		 
	} 
	
	@Test
	public void test2(){
		Event evt = new Event();
		evt.setTitle("hello");
		evt.setStartTime(new Date());
		evt.setEndTime(new Date());
		evt.setLocation("Shenzhen");
		evt.setDescription("test");
		Category cate = new Category();
		cate.setId("1");
		evt.setEventCategory(cate);
		
		List<User> attendees = new ArrayList<User>();
		
		User u1 = new User();
		u1.setId("18");
		attendees.add(u1);
		
		
		User u2 = new User();
		u2.setId("19");
		attendees.add(u2);
		evt.setAttendees(attendees);
		
		User owner = new User();
		owner.setId("21");
		evt.setOwner(owner);
		
		evt.setEventStatus(EventStatus.INIT);
		evt.setVisibility(EventVisibility.V_PUBLIC);
		evt = eventDao.create(evt);
	}
	
//	@Test
	public void testMany2ManyRead(){
//		Event e = eventDataService.read("33");	
//		for(User u : e.getAttendees()){
//			System.out.println(e.getTitle() + " -- User:" + u.getId());
//		}
	}
	

	
}
