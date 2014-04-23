package com.goparty.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.junit.Test;

import com.goparty.data.constant.EventStatus;
import com.goparty.data.constant.EventVisibility;
import com.goparty.data.constant.InvitationAcceptance;
import com.goparty.data.model.Category;
import com.goparty.data.model.Event;
import com.goparty.data.model.User;
import com.goparty.webservice.model.EventInvitationRequest;
import com.goparty.webservice.model.EventInviteRequest;
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
		u1.setAdmin(true);
		attendees.add(u1);

		User u2 = new User();
		u2.setId("19");
		attendees.add(u2);
		evt.setMembers(attendees);

		User owner = new User();
		owner.setId("18");
		evt.setOwner(owner);

		evt.setStatus(EventStatus.INIT);
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
				+ result.getId()+"/invitees/97";
		
		HttpClientUtils.put(url, data);
		
		HttpClientUtils.delete(url);
		
		url = "http://localhost/cxf/rest/events/"
				+ result.getId()+"/sponsors/19";
		
		HttpClientUtils.put(url, data);

        url = "http://localhost/cxf/rest/events/"
				+ result.getId();
        HttpClientUtils.get(url);
		
        HttpClientUtils.delete(url);
	}
	
	@Test
	public void testEventList() throws Exception{
		String url = "http://localhost/cxf/rest/events?scope={all}&after=&before=&categories=&search=&offset=&limit=";
		
		String resp = HttpClientUtils.get(url);
		
		System.out.println(resp);
	}
	
	@Test
	public void testInviteEvent() throws Exception{
		String url = "http://localhost/cxf/rest/events/16/members/33-96";

		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'"));
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
		mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
		
		EventInviteRequest request = new EventInviteRequest();
		request.setMessage("test");
				
		String data = mapper.writeValueAsString(request).toString();
		

		String token = "4e8bb1e4-4fab-4c4e-9a9f-cf5ece4cc2aa";
		HttpUtils http = new HttpUtils(token);
		
		String resp = http.postData(url,data);
		
		System.out.println(resp);
	}
	
	@Test
	public void testGetUnrespondedInvitations() throws Exception{
		String url = "http://localhost/cxf/rest/events/unrespondedInvitations?offset=0&limit=30";

		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'"));
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
		mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
		
		String token = "84a70b99-5b09-4ba3-b4f8-5fe49e7ad83c";
		HttpUtils http = new HttpUtils(token);
		
		String resp = http.getData(url);
		
		System.out.println(resp);
	}
	
	@Test
	public void testRespondedInvitations() throws Exception{
		String url = "http://localhost/cxf/rest/events/unrespondedInvitations/9";

		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'"));
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
		mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
		
		String token = "84a70b99-5b09-4ba3-b4f8-5fe49e7ad83c";
		HttpUtils http = new HttpUtils(token);
		
		EventInvitationRequest request = new EventInvitationRequest();
		request.setInviteeMessage("yes");
		request.setIgnore("N");
		request.setParticipance(InvitationAcceptance.Y.toString());
				
		String data = mapper.writeValueAsString(request).toString();
		
		
		String resp = http.putData(url,data);
		
		System.out.println(resp);
	}
	
}
