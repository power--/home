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
import com.goparty.data.model.Event;
import com.goparty.data.model.EventCategory;
import com.goparty.data.model.UserProfile;
 

public class EventDataServiceTest extends AbstractRepositoryTest {

	@Autowired
	private EventDataService eventDataService;
	 

	
//	@Test
	public void test() {
		Event event = new Event(); 
		event.setDescription("Hello World");
		event.setTitle("A Title");
		event.setEventStatus(EventStatus.INIT);
		
		UserProfile owner = new UserProfile();
		owner.setId("21");
		event.setOwner(owner);
		
		List<UserProfile> attendees = new ArrayList<UserProfile>();
		UserProfile user1 = new UserProfile();
		user1.setId("18");  
		UserProfile user2 = new UserProfile();
		user2.setId("19");  

		attendees.add(owner);
		attendees.add(user1);
		attendees.add(user2);		
		event.setAttendees(attendees); 
		
		EventCategory cate = new EventCategory();
		cate.setId("1");
		event.setEventCategory(cate);		
		event = eventDataService.create(event);
		
		Event e = eventDataService.read(event.getId()); 
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
		EventCategory cate = new EventCategory();
		cate.setId("1");
		evt.setEventCategory(cate);
		
		List<UserProfile> attendees = new ArrayList<UserProfile>();
		
		UserProfile u1 = new UserProfile();
		u1.setId("18");
		attendees.add(u1);
		
		
		UserProfile u2 = new UserProfile();
		u2.setId("19");
		attendees.add(u2);
		evt.setAttendees(attendees);
		
		UserProfile owner = new UserProfile();
		owner.setId("21");
		evt.setOwner(owner);
		
		evt.setEventStatus(EventStatus.INIT);
		evt.setVisibility(EventVisibility.V_PUBLIC);
		evt = eventDataService.create(evt);
	}
	
//	@Test
	public void testMany2ManyRead(){
//		Event e = eventDataService.read("33");	
//		for(User u : e.getAttendees()){
//			System.out.println(e.getTitle() + " -- User:" + u.getId());
//		}
	}
	

	
}
