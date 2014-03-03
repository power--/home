package com.goparty.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goparty.data.constant.EventStatus;
import com.goparty.data.constant.EventVisibility;
import com.goparty.data.model.Event;
import com.goparty.data.model.EventCategory;
import com.goparty.data.model.User;
import com.goparty.webservice.EventService;

public class EventTest {
	private EventService eventService;
	private static final String applicationURI = "http://goparty.cloudapp.net";
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before
	public void setUp() {
		List<Object> providers = new LinkedList<Object>();

		eventService = JAXRSClientFactory.create(applicationURI + "/cxf/rest/",
				EventService.class, providers, true);
		ClientConfiguration cfgProxy = WebClient.getConfig(eventService);
		cfgProxy.getOutInterceptors().add(new LoggingOutInterceptor());
		cfgProxy.getInInterceptors().add(new LoggingInInterceptor());
	}

	public void test() {
		Event evt = new Event();
		evt.setTitle("hello");
		evt.setStartTime(new Date());
		evt.setEndTime(new Date());
		evt.setLocation("Shenzhen");
		evt.setDescription("test");
		EventCategory cate = new EventCategory();
		cate.setId("1");
		evt.setEventCategory(cate);

		List<User> attendees = new ArrayList<User>();

		User u1 = new User();
		u1.setId("18");
		attendees.add(u1);

		User u2 = new User();
		u2.setId("19");
		attendees.add(u2);
		evt.setAttendees(attendees);

		User owner = new User();
		owner.setId("12");
		evt.setOwner(owner);

		evt.setStatus(EventStatus.INIT);
		evt.setVisibility(EventVisibility.V_PUBLIC);
		evt = eventService.create(evt);

		evt = eventService.read(evt.getId());

		evt = eventService.update(evt);

		eventService.delete(evt.getId());
	}

	@Test
	public void testJson() throws Exception {
		Event evt = new Event();
		evt.setTitle("hello");
		evt.setStartTime(new Date());
		evt.setEndTime(new Date());
		evt.setLocation("Shenzhen");
		evt.setDescription("test");
		EventCategory cate = new EventCategory();
		cate.setId("1");
		evt.setEventCategory(cate);

		List<User> attendees = new ArrayList<User>();

		User u1 = new User();
		u1.setId("18");
		attendees.add(u1);

		User u2 = new User();
		u2.setId("19");
		attendees.add(u2);
		evt.setAttendees(attendees);

		User owner = new User();
		owner.setId("12");
		evt.setOwner(owner);

		evt.setStatus(EventStatus.INIT);
		evt.setVisibility(EventVisibility.V_PUBLIC);

		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'"));
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
		mapper.configure(
				DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
				true);

		String json = mapper.writeValueAsString(evt).toString();

		System.out.println("POST");
		System.out.println(json);
		String resp = this.sendData("POST", json);
		System.out.println("RESP");
		System.out.println(resp);

		Event result = mapper.readValue(resp, Event.class);
		result.setTitle("helloX+++++++++++++++++++++");
		json = mapper.writeValueAsString(result).toString();

		System.out.println("PUT");
		System.out.println(json);
		resp = this.sendData("POST", json);
		System.out.println("RESP");
		System.out.println(resp);

		System.out.println("GET");
		String url = "http://goparty.cloudapp.net/cxf/rest/events/"
				+ result.getId();
		System.out.println(url);
		resp = this.getData(url);
		System.out.println(resp);
		
		System.out.println("DELETE");
		System.out.println(url);
		resp = this.deleteData(url);
		System.out.println(resp);
	}

	private String deleteData(String url) throws Exception {
		String ret = null;

		HttpURLConnection conn = null;
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("DELETE");
			con.setRequestProperty("Accept", "application/json");

			// add request header

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'DELETE' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			ret = response.toString();

		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return ret;
	}

	private String getData(String url) throws Exception {
		String ret = null;

		HttpURLConnection conn = null;
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");

			// add request header

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			ret = response.toString();

		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return ret;
	}

	private String sendData(String method, String json) {
		String ret = null;
		String http = "http://goparty.cloudapp.net/cxf/rest/events";

		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(http);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod(method);
			urlConnection.setUseCaches(false);
			urlConnection.setConnectTimeout(10000);
			urlConnection.setReadTimeout(10000);
			urlConnection
					.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestProperty("Accept", "application/json");
			urlConnection.setRequestProperty("charset", "utf-8");

			urlConnection.setRequestProperty("Content-Length",
					"" + json.getBytes("UTF-8").length);

			OutputStreamWriter out = new OutputStreamWriter(
					urlConnection.getOutputStream());
			out.write(json);
			out.close();

			int HttpResult = urlConnection.getResponseCode();
			StringBuffer sb = new StringBuffer();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				InputStream input = urlConnection.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						input, "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				ret = sb.toString();
			} else {
				System.out.println(urlConnection.getResponseMessage());
			}
		} catch (Exception e) {
			logger.error("error", e);
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
		}
		return ret;
	}

	public void testAddEvent() throws Exception {
		String http = "http://goparty.cloudapp.net/cxf/rest/events";

		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(http);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setUseCaches(false);
			urlConnection.setConnectTimeout(10000);
			urlConnection.setReadTimeout(10000);
			urlConnection
					.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestProperty("Accept", "application/json");
			urlConnection.setRequestProperty("charset", "utf-8");

			String jsonStr = "{\"attendees\":[{\"id\":21,\"loginId\":\"user21\"},{\"id\":88,\"loginId\":\"user21\"}],\"description\":\"Hello World\",\"endTime\":\"2014-02-19\",\"eventCategory\":{\"id\":1,\"name\":\"KTV \"},\"owner\":{\"id\":21,\"loginId\":\"user21\"},\"startTime\":\"2014-02-16T10:23:10Z\",\"status\":\"INIT\",\"title\":\"A Title\"}";

			System.out.println("" + jsonStr.toString());
			urlConnection.setRequestProperty("Content-Length",
					"" + jsonStr.getBytes("UTF-8").length);

			OutputStreamWriter out = new OutputStreamWriter(
					urlConnection.getOutputStream());
			out.write(jsonStr);
			out.close();

			int HttpResult = urlConnection.getResponseCode();
			StringBuffer sb = new StringBuffer();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				InputStream input = urlConnection.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						input, "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();

				System.out.println("" + sb.toString());

			} else {
				System.out.println(urlConnection.getResponseMessage());
			}
		} catch (Exception e) {
			logger.error("error", e);
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
		}
	}
}
