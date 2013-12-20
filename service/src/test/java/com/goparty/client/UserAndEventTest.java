package com.goparty.client;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.junit.Before;
import org.junit.Test;

import com.goparty.biz.model.*;
import com.goparty.webservice.EventService;
import com.goparty.webservice.LocationService;
import com.goparty.webservice.UserService;

public class UserAndEventTest {
	private EventService eventService;
	private UserService userService;
	private static final String applicationURI ="http://localhost";
	
	
	@Before
	public void setUp(){
		List<Object> providers = new LinkedList<Object>();
		
		eventService = JAXRSClientFactory.create(applicationURI + "/cxf/rest/", EventService.class, providers, true);
		userService = JAXRSClientFactory.create(applicationURI + "/cxf/rest/", UserService.class, providers, true);
	}  
	
	@Test
	public void test(){
		User owner = new User();
		owner.setNickName("Bo");
		owner.setUserName("chenb");
		owner.setPassword("password");
		
		User att1 = new User();
		owner.setNickName("att1");
		owner.setUserName("att1");
		owner.setPassword("password");
		
		User att2 = new User();
		owner.setNickName("att2");
		owner.setUserName("att2");
		owner.setPassword("password");
		
		owner  = userService.addUser(owner);
		att1 = userService.addUser(att1);
		att2 = userService.addUser(att2);
		
		Event event = new Event();
		event.setOwner(owner);
		List<User> attendees = new ArrayList<User>();
		event.setAttendees(attendees);
		event.setDescription("Hello World");
		event.setTitle("A Title");
		
		EventCategory c = new EventCategory();
		c.setId("1");
		c.setName("Company");
		event.setEventCategory(c);
		
		EventStatus s = new EventStatus();
		s.setId("1");
		s.setName("Draft");
		event.setEventStatus(s);
		
		VisiblityCategory v = new VisiblityCategory();
		v.setId("1");
		v.setName("public");
		event.setVisiblityCategory(v);
		
		event = eventService.addEvent(event);
		
		eventService.deleteEvent(event.getId());
		userService.deleteUser(att1.getId());
		userService.deleteUser(att2.getId());
		userService.deleteUser(owner.getId());
	}
}
