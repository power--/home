package com.goparty.client;

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

import com.goparty.data.model.Event;
import com.goparty.webservice.EventService;

public class EventTest {
	private EventService eventService;
	private static final String applicationURI ="http://localhost";
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Before
	public void setUp(){
		List<Object> providers = new LinkedList<Object>();
		
		eventService = JAXRSClientFactory.create(applicationURI + "/cxf/rest/", EventService.class, providers, true);
		ClientConfiguration cfgProxy = WebClient.getConfig(eventService);
		cfgProxy.getOutInterceptors().add(new LoggingOutInterceptor());
		cfgProxy.getInInterceptors().add(new LoggingInInterceptor());
	} 
	
	@Test
	public void test(){
		Event evt = new Event();
		evt.setDescription("test");
		
		evt = eventService.create(evt);
		
		evt = eventService.read(evt.getId());
		
		evt = eventService.update(evt);
		
		eventService.delete(evt.getId());
		
		
	}
}
