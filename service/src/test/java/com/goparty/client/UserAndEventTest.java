package com.goparty.client;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goparty.biz.model.*;
import com.goparty.webservice.EventService;
import com.goparty.webservice.LocationService;
import com.goparty.webservice.UserService;

public class UserAndEventTest {
	private EventService eventService;
	private UserService userService;
	private static final String applicationURI ="http://localhost";
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Before
	public void setUp(){
		List<Object> providers = new LinkedList<Object>();
		
		eventService = JAXRSClientFactory.create(applicationURI + "/cxf/rest/", EventService.class, providers, true);
		ClientConfiguration cfgProxy = WebClient.getConfig(eventService);
		cfgProxy.getOutInterceptors().add(new LoggingOutInterceptor());
		cfgProxy.getInInterceptors().add(new LoggingInInterceptor());
		
		userService = JAXRSClientFactory.create(applicationURI + "/cxf/rest/", UserService.class, providers, true);
		cfgProxy = WebClient.getConfig(userService);
		cfgProxy.getOutInterceptors().add(new LoggingOutInterceptor());
		cfgProxy.getInInterceptors().add(new LoggingInInterceptor());
	}  
	
	@Test
	public void test(){
		User owner = new User();
		owner.setNickName("Bo");
		owner.setUserName("chenb");
		owner.setPassword("password");
		logger.error("******START*************************************************");
		owner = userService.create(owner);
		logger.error("******END*************************************************");
		logger.error("******START*************************************************");
		owner = userService.read(owner.getId());
		logger.error("******END*************************************************");
		logger.error("******START*************************************************");
		owner = userService.update(owner);
		logger.error("******END*************************************************");
		logger.error("******START*************************************************");
		userService.delete(owner.getId());
		logger.error("******END*************************************************");
		
		
		User att1 = new User();
		att1.setNickName("att1");
		att1.setUserName("att1");
		att1.setPassword("password");
		
		User att2 = new User();
		att2.setNickName("att2");
		att2.setUserName("att2");
		att2.setPassword("password");
		
		owner  = userService.create(owner);
		att1 = userService.create(att1);
		att2 = userService.create(att2);
		
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
		
		event = eventService.create(event);
		event = eventService.read(event.getId());
		event = eventService.update(event);
		eventService.delete(event.getId());
		
		userService.delete(att1.getId());
		userService.delete(att2.getId());
		userService.delete(owner.getId());
	}
}
