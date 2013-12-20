package com.goparty.client;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goparty.biz.model.*;
import com.goparty.webservice.EventService;
import com.goparty.webservice.LocationService;
import com.goparty.webservice.UserService;

public class UserAndEventTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private EventService eventService;
	private UserService userService;
	private static final String applicationURI ="http://goparty.cloudapp.net";
	
	
	@Before
	public void setUp(){
		List<Object> providers = getJsonProvider();
		
		eventService = JAXRSClientFactory.create(applicationURI + "/cxf/rest/", EventService.class, providers, true);
		ClientConfiguration cfgProxy = WebClient.getConfig(eventService);
		cfgProxy.getOutInterceptors().add(new LoggingOutInterceptor());
		cfgProxy.getInInterceptors().add(new LoggingInInterceptor());

		
		
		userService = JAXRSClientFactory.create(applicationURI + "/cxf/rest/", UserService.class, providers, true);
		cfgProxy = WebClient.getConfig(userService);
		cfgProxy.getOutInterceptors().add(new LoggingOutInterceptor());
		cfgProxy.getInInterceptors().add(new LoggingInInterceptor());
	} 
	
	

	private static List<Object> getJsonProvider(){ 
		List<Object> providers = new LinkedList<Object>(); 
		JSONProvider jsonProvider = new JSONProvider();
		providers.add(jsonProvider); 
        return providers; 
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
		
		logger.error("*******************************");
		owner  = userService.getUser(owner.getId());
		logger.error("*******************************");
		owner.setNickName("Chen, Bo");
		userService.updateUser(owner);
		
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
		event.setDescription("Hi Man");
		eventService.updateUser(event);
		
		logger.error("*******************************");
		event = eventService.getEvent(event.getId());
		logger.error("*******************************");
		eventService.deleteEvent(event.getId());
		userService.deleteUser(att1.getId());
		userService.deleteUser(att2.getId());
		userService.deleteUser(owner.getId());
	}
}
