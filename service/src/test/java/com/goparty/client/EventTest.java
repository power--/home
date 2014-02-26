package com.goparty.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
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
import com.goparty.data.model.EventCategory;
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
		EventCategory cate = new EventCategory();
		cate.setId("1");
		evt.setEventCategory(cate);		
		evt = eventService.create(evt);
		
		evt = eventService.read(evt.getId());
		
		evt = eventService.update(evt);
		
		eventService.delete(evt.getId()); 
	}
	
	@Test
	public void testAddEvent() throws Exception {
		String http = "http://goparty.cloudapp.net/cxf/rest/event";

		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(http);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setUseCaches(false);
			urlConnection.setConnectTimeout(10000);
			urlConnection.setReadTimeout(10000);
			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestProperty("Accept", "application/json");
			urlConnection.setRequestProperty("charset", "utf-8");

			String jsonStr = "{\"attendees\":{\"attendees\":[{\"id\":21,\"userName\":\"user21\"},{\"id\":88,\"userName\":\"user21\"}]},\"description\":\"Hello World\",\"endTime\":\"2014-02-19\",\"eventCategory\":{\"id\":1,\"name\":\"KTV \"},\"owner\":{\"id\":21,\"userName\":\"user21\"},\"startTime\":\"2014-02-16T10:23:10Z\",\"status\":\"INIT\",\"title\":\"A Title\"}";

			System.out.println("" + jsonStr.toString());
			urlConnection.setRequestProperty("Content-Length","" + jsonStr.getBytes("UTF-8").length);

			OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
			out.write(jsonStr);
			out.close();

			int HttpResult = urlConnection.getResponseCode();
			StringBuffer sb = new StringBuffer();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				InputStream input = urlConnection.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(input, "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();

				System.out.println("" + sb.toString());

			} else {
				System.out.println(urlConnection.getResponseMessage());
			}
		}catch (Exception e) {
			logger.error("error",e);
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
		}
	}
}
