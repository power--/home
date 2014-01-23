package com.goparty.data.service;
 

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goparty.data.constant.EventStatus;
import com.goparty.data.model.Event;
import com.goparty.data.model.EventCategory;
import com.goparty.data.model.User;
 

public class EventDataServiceTest extends AbstractRepositoryTest {

	@Autowired
	private EventDataService eventDataService;
	 

	
	@Test
	public void test() {
		Event event = new Event(); 
		List<User> attendees = new ArrayList<User>();
		event.setAttendees(attendees);
		event.setDescription("Hello World");
		event.setTitle("A Title");
		event.setStatus(EventStatus.INIT.name());
		
		User owner = new User();
		owner.setId("21");
		event.setOwner(owner);
		
		EventCategory cate = new EventCategory();
		cate.setId("1");
		event.setEventCategory(cate);		
		event = eventDataService.create(event);
		
		Event e = eventDataService.read(event.getId());		 
		System.out.println(e.getTitle());
		assertEquals("Hello World",e.getDescription());
		assertNotNull(e.getOwner().getNickName());
		 
	} 
	
	
}
