package com.goparty.webservice.impl;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goparty.data.constant.MessageType;
import com.goparty.data.exception.BaseException;
import com.goparty.data.model.*;
import com.goparty.data.service.EventDataService;
import com.goparty.data.service.MessageDataService;
import com.goparty.data.service.UserDataService;
import com.goparty.webservice.EventService;
import com.goparty.webservice.model.MessageRequest;

@Service("eventService")
public class EventServiceImpl implements EventService {
	@Autowired
	private EventDataService eventDataService;
	
	@Autowired
	private UserDataService userDataService;

	@Autowired
	private MessageDataService messageDataService;
	
	@Override
	public Event read(String id) {
		Event ret = eventDataService.read(id);
		return ret;
	}

	@Override
	public Event create(Event event){
		if(event.getTitle()==null){
			throw new BaseException("The event title should not be null");
		}
		
		System.out.println(event.getId());
		System.out.println(event.getId());
		eventDataService.create(event);
		
		UserProfile user = new UserProfile();
		user.setId("18");
		List<UserProfile> refusedAttendees = new ArrayList<UserProfile>();
		refusedAttendees.add(user);
		event.setRefusedAttendees(refusedAttendees);
		return event;
	}

	@Override
	public Event update(Event event) {
		eventDataService.update(event);
		return event;
	}

	@Override
	public boolean delete(String id) {
		boolean ret = false;
		try{
			eventDataService.delete(id);
			ret = true;
		}catch(Exception ex){
			throw ex;
		}
		return ret;
	}

	@Override
	public BaseModel addInvitee(String eventId, String userId) {
		BaseModel ret = new BaseModel();
		
		Event evt = eventDataService.read(eventId);
		
		if(evt == null){
			ret.setCode(INTERNAL_SERVER_ERROR.getStatusCode());
			ret.setStatus(INTERNAL_SERVER_ERROR.toString());
			ret.setMessage("no_such_event");
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("eventId", eventId);
			ret.setData(data);
			return ret;
		}
		
		UserProfile user = userDataService.read(userId);
		if(user == null){
			ret.setCode(INTERNAL_SERVER_ERROR.getStatusCode());
			ret.setStatus(INTERNAL_SERVER_ERROR.toString());
			ret.setMessage("no_such_user");
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("userId", userId);
			return ret;
		}
		
		if(evt.getAttendees().contains(user)){
			ret.setCode(INTERNAL_SERVER_ERROR.getStatusCode());
			ret.setStatus(INTERNAL_SERVER_ERROR.toString());
			ret.setMessage("user_existed_in_attendees.");
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("userId", userId);
			data.put("eventId", eventId);
			return ret;
		}
		
		evt.getAttendees().add(user);
		eventDataService.update(evt);
		
		ret.setCode(OK.getStatusCode());
		ret.setStatus(OK.toString());
		ret.setMessage("success");
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("userId", userId);
		data.put("eventId", eventId);
		
		return ret;
	}

	@Override
	public BaseModel delInvitee(String eventId, String userId) {
        BaseModel ret = new BaseModel();
		
		Event evt = eventDataService.read(eventId);
		
		if(evt == null){
			ret.setCode(INTERNAL_SERVER_ERROR.getStatusCode());
			ret.setStatus(INTERNAL_SERVER_ERROR.toString());
			ret.setMessage("no_such_event");
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("eventId", eventId);
			return ret;
		}
		
		UserProfile user = userDataService.read(userId);
		if(user == null){
			ret.setCode(INTERNAL_SERVER_ERROR.getStatusCode());
			ret.setStatus(INTERNAL_SERVER_ERROR.toString());
			ret.setMessage("no_such_user");
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("userId", userId);
			return ret;
		}
		
		if(!evt.getAttendees().contains(user)){
			ret.setCode(INTERNAL_SERVER_ERROR.getStatusCode());
			ret.setStatus(INTERNAL_SERVER_ERROR.toString());
			ret.setMessage("user_not_in_attendees.");
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("userId", userId);
			data.put("eventId", eventId);
			return ret;
		}
		
		evt.getAttendees().remove(user);
		eventDataService.update(evt);
		
		ret.setCode(OK.getStatusCode());
		ret.setStatus(OK.toString());
		ret.setMessage("success");
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("userId", userId);
		data.put("eventId", eventId);
		
		return ret;
	}

	@Override
	public BaseModel updateSponser(String eventId, String userId) {
BaseModel ret = new BaseModel();
		
		Event evt = eventDataService.read(eventId);
		
		if(evt == null){
			ret.setCode(INTERNAL_SERVER_ERROR.getStatusCode());
			ret.setStatus(INTERNAL_SERVER_ERROR.toString());
			ret.setMessage("no_such_event");
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("eventId", eventId);
			return ret;
		}
		
		UserProfile user = userDataService.read(userId);
		if(user == null){
			ret.setCode(INTERNAL_SERVER_ERROR.getStatusCode());
			ret.setStatus(INTERNAL_SERVER_ERROR.toString());
			ret.setMessage("no_such_user");
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("userId", userId);
			return ret;
		}
		
		if(evt.getOwner().equals(user)){
			ret.setCode(INTERNAL_SERVER_ERROR.getStatusCode());
			ret.setStatus(INTERNAL_SERVER_ERROR.toString());
			ret.setMessage("cannot_use_yourself");
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("userId", userId);
			data.put("eventId", eventId);
			return ret;
		}
		
		evt.setOwner(user);
		eventDataService.update(evt);
		
		ret.setCode(OK.getStatusCode());
		ret.setStatus(OK.toString());
		ret.setMessage("success");
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("userId", userId);
		data.put("eventId", eventId);
		
		return ret;
	}

	@Override
	public EventMessage publishMessage(String token, String eventId, MessageRequest request) {
		UserProfile user = userDataService.getUserByToken(token);
		EventMessage msg = new EventMessage();
		msg.setUserId(user.getId());
		msg.setEventId(eventId);
		msg.setContent(request.getContent());
		msg.setPublishTime(new Date());
		msg.setType(MessageType.USER_MESSAGE);
		msg = messageDataService.create(msg );
		return msg;
	}

	@Override
	public List<EventMessage> getMessageListByEventId(String eventId,int offset,int limit) {		
		return messageDataService.findByEventId(eventId, offset, limit);
	}
	
	
}
