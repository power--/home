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

public class CommentTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	  

	private String token = "5397efef-01ef-4d4b-aef8-260508af81bf";
	
	 
	
	
	@Test
	public void testAdd() throws Exception {
		HttpUtils http = new HttpUtils(token);
		String url = "http://localhost/cxf/rest/events/12/comments";
		String json = "{\"content\": \"love you too!\" }";
		String response = http.postData(url, json);
		System.out.println(response);	 
		 
	}
	
	
	 
}
