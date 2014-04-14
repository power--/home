package com.goparty.client;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
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

import com.goparty.data.model.Group;
import com.goparty.data.model.UserFriend;
import com.goparty.webservice.FriendService;
import com.goparty.webservice.model.FriendRequest;
import com.goparty.webservice.model.FriendInvitationRequest;

public class FriendTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private FriendService friendService; 
	private String applicationURI = "http://localhost/cxf/rest";

	private String token = "4e8bb1e4-4fab-4c4e-9a9f-cf5ece4cc2aa";
	private HttpUtils http = new HttpUtils(token);
	
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
	public void testInvite() throws Exception {  
		String url = applicationURI + "/friends/97"; 
		String content = "{\"message\": \"我是time\"}";
		String response = http.postData(url, content);
		System.out.println(response);
	}
	
	@Test
	public void testRespInvitation() throws Exception{ 
		String url = applicationURI + "/friends/unrespondedInvitations/2";  
		String content = "{\"groups\": [{\"id\":16},{\"id\":17}],\"acceptance\": \"Y\",\"message\": \"fuck you!!\"}";
		String response = http.putData(url, content);
		System.out.println(response);
	}
	
	
	@Test
	public void testGetUnRespInvitation() throws Exception{ 
		String url = applicationURI + "/friends/unrespondedInvitations?offset=0&limit=5";  
		String response = http.getData(url);
		System.out.println(response);
	}
	
	@Test
	public void testGetRespInvitation() throws Exception{ 
		String url = applicationURI + "/friends/respondedInvitations?offset=0&limit=5";  
		String response = http.getData(url);
		System.out.println(response);
	}
	
	
	@Test
	public void testUpdateFriend() throws Exception {  
		String url = applicationURI + "/friends/97"; 
		String content = "{\"groups\": [{\"id\":16},{\"id\":17}],\"remarkName\": \"Tim\"}";
		String response = http.putData(url, content);
		System.out.println(response);
	}
	
	@Test
	public void testDeleteFriend() throws Exception {  
		String url = applicationURI + "/friends/97";  
		String response = http.deleteData(url);
		System.out.println(response);
	}
	
	@Test
	public void testGetFriend() throws Exception {  
		String url = applicationURI + "/friends?offset=0&limit=5";  
		String response = http.getData(url);
		System.out.println(response);
	}
	
	
	
	
	
	@Test
	public void testAdd() throws Exception {
		HttpUtils http = new HttpUtils(token);
		String url = "http://localhost/cxf/rest/friends/groups";
		String json = "{\"groupName\": \"bb\" }";
		String response = http.postData(url, json);
		System.out.println(response);	 
		
		String updateUrl =  "http://localhost/cxf/rest/friends/groups/6";
		String updateJson = "{\"groupName\": \"private girl friend \" }";
		String updateResp = http.putData(updateUrl, updateJson);
		System.out.println(updateResp);	
		
		String deleteResp = http.deleteData("http://localhost/cxf/rest/friends/groups/7");
		System.out.println(deleteResp);
	}
	
	
	 
}
