package com.goparty.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.junit.Test;
 
 
import com.goparty.data.constant.EventStatus;
import com.goparty.data.constant.EventVisibility;
import com.goparty.data.model.Event;
import com.goparty.data.model.Category;
import com.goparty.data.model.User;
import com.goparty.webservice.utils.BaseData;

public class EventTest {

	@Test
	public void testEvent() throws Exception {
		Event evt = new Event();
		evt.setTitle("hello");
		evt.setStartTime(new Date());
		evt.setEndTime(new Date());
		evt.setLocation("Shenzhen");
		evt.setDescription("test");
		Category cate = new Category();
		cate.setId("1");
		List<Category> cates = new ArrayList<Category>();
		cates.add(cate);
		evt.setCategories(cates);
		

		List<User> attendees = new ArrayList<User>();

		User u1 = new User();
		u1.setId("18");
		attendees.add(u1);

		User u2 = new User();
		u2.setId("19");
		attendees.add(u2);
		evt.setAttendees(attendees);

		User owner = new User();
		owner.setId("33");
		evt.setOwner(owner);

		evt.setEventStatus(EventStatus.INIT);
		evt.setVisibility(EventVisibility.V_PUBLIC);

		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'"));
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
		mapper.configure(
				DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
				true);
		

				
				
		String data = mapper.writeValueAsString(evt).toString();
		String url = "http://localhost/cxf/rest/events";
		
		String resp = HttpClientUtils.post(url, data);
		
		System.out.println(resp);
		
		BaseData res = mapper.readValue(resp, BaseData.class);		
		Event result = mapper.readValue(res.getData(), Event.class);
		result.setTitle("hello PUT");
		data = mapper.writeValueAsString(result).toString();
		HttpClientUtils.put(url, data);

		url = "http://localhost/cxf/rest/events/"
				+ result.getId()+"/invitees/72";
		
		HttpClientUtils.put(url, data);
		
		HttpClientUtils.delete(url);
		
		url = "http://localhost/cxf/rest/events/"
				+ result.getId()+"/sponsors/33";
		
		HttpClientUtils.put(url, data);

        url = "http://localhost/cxf/rest/events/"
				+ result.getId();
        HttpClientUtils.get(url);
		
        HttpClientUtils.delete(url);
	}
}
