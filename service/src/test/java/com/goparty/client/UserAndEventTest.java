package com.goparty.client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
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

	//@Test 
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
		
		owner  = userService.create(owner);
		
		logger.error("*******************************");
		owner  = userService.read(owner.getId());
		logger.error("*******************************");
		owner.setNickName("Chen, Bo");
		userService.update(owner);
		
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
		
		VisibilityCategory v = new VisibilityCategory();
		v.setId("1");
		v.setName("public");
		event.setVisibilityCategory(v);
		
		event = eventService.create(event);
		event.setDescription("Hi Man");
		eventService.update(event);
		
		logger.error("*******************************");
		event = eventService.read(event.getId());
		logger.error("*******************************");
		eventService.delete(event.getId());
		userService.delete(att1.getId());
		userService.delete(att2.getId());
		userService.delete(owner.getId());
	}
	
	@Test
	public void testUploadImage() throws IOException{
		//URL url = new URL("http://www.baidu.com/img/bdlogo.gif");
		URL url = new URL("http://www.solutionoferror.com/images/soe_logo.jpg");
		URLConnection conn = url.openConnection();
		
		BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
		Attachment attachment = new Attachment(null,conn.getContentType(),bis);
		String userId = "dd9538ef-5755-4280-8cdf-5ff3b179e858";
		//List<Attachment> image = new ArrayList<Attachment>();
		//image.add(attachment);
		 
		List<Attachment> atts = new LinkedList<Attachment>();
	    atts.add(new Attachment("image", conn.getContentType(), bis)); 
	      
		String fileName = userService.uploadImage(new MultipartBody(atts, true),userId);
		logger.info("fileName : " + fileName) ; 
		bis.close();
	}
}
