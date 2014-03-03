package com.goparty.client;

import static org.junit.Assert.*;

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

import com.goparty.data.model.UserFriend;
import com.goparty.webservice.FriendService;
import com.goparty.webservice.model.InvitationRequest;

public class FriendTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private FriendService friendService; 
	private static final String applicationURI ="http://localhost";
	
	
	@Before
	public void setUp(){
		List<Object> providers = getJsonProvider();
		
		friendService = JAXRSClientFactory.create(applicationURI + "/cxf/rest/", FriendService.class, providers, true);
		ClientConfiguration cfgProxy = WebClient.getConfig(friendService);
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
		String token = "5397efef-01ef-4d4b-aef8-260508af81bf";
		List<UserFriend> list = friendService.getFriendInvitationList(token);
		assertTrue(list.size()>0);
		
		InvitationRequest request = new InvitationRequest();
		request.setResponse("AGREE");		
		request.setMessage("Good Boy");
		friendService.respondFriendInvitation(token, "35", request);
		
		
		
	}
	
	 
}
