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
import com.goparty.data.dao.CommentDao;
import com.goparty.data.dao.EventDao;
import com.goparty.data.dao.MessageDao;
import com.goparty.data.dao.UserDao;
import com.goparty.data.exception.BaseException;
import com.goparty.data.model.*;
import com.goparty.webservice.EventService;
import com.goparty.webservice.model.CommentRequest;
import com.goparty.webservice.model.MessageRequest;
import com.goparty.webservice.utils.ResponseUtil;

@Service("eventService")
public class EventServiceImpl implements EventService {
	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private MessageDao messageDataService;
	
	@Autowired
	private CommentDao commentDao;
	
	
	@Override
	public Response read(String id) {
		Event ret = eventDao.read(id);
		return ResponseUtil.buildResponse(ret);
	}

	@Override
	public Response create(Event event){
		if(event.getTitle()==null){
			throw new BaseException("The event title should not be null");
		}
		
		System.out.println(event.getId());
		System.out.println(event.getId());
		eventDao.create(event);
		
		User user = new User();
		user.setId("18");
		List<User> refusedAttendees = new ArrayList<User>();
		refusedAttendees.add(user);
		event.setRefusedAttendees(refusedAttendees);
		return ResponseUtil.buildResponse(event);
	}

	@Override
	public Response update(Event event) {
		eventDao.update(event);
		return ResponseUtil.buildResponse(event);
	}

	@Override
	public Response delete(String id) {
		boolean ret = false;
		try{
			eventDao.delete(id);
			ret = true;
		}catch(Exception ex){
			throw ex;
		}
		return ResponseUtil.buildResponse(ret);
	}

	@Override
	public Response addInvitee(String eventId, String userId) {
		BaseModel ret = new BaseModel();
		
		Event evt = eventDao.read(eventId);
		
		if(evt == null){
			ret.setCode(INTERNAL_SERVER_ERROR.getStatusCode());
			ret.setStatus(INTERNAL_SERVER_ERROR.toString());
			ret.setMessage("no_such_event");
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("eventId", eventId);
			ret.setData(data);
			return ResponseUtil.buildResponse(ret);
		}
		
		User user = userDao.read(userId);
		if(user == null){
			ret.setCode(INTERNAL_SERVER_ERROR.getStatusCode());
			ret.setStatus(INTERNAL_SERVER_ERROR.toString());
			ret.setMessage("no_such_user");
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("userId", userId);
			return ResponseUtil.buildResponse(ret);
		}
		
		if(evt.getAttendees().contains(user)){
			ret.setCode(INTERNAL_SERVER_ERROR.getStatusCode());
			ret.setStatus(INTERNAL_SERVER_ERROR.toString());
			ret.setMessage("user_existed_in_attendees.");
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("userId", userId);
			data.put("eventId", eventId);
			return ResponseUtil.buildResponse(ret);
		}
		
		evt.getAttendees().add(user);
		eventDao.update(evt);
		
		ret.setCode(OK.getStatusCode());
		ret.setStatus(OK.toString());
		ret.setMessage("success");
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("userId", userId);
		data.put("eventId", eventId);
		
		return ResponseUtil.buildResponse(ret);
	}

	@Override
	public Response delInvitee(String eventId, String userId) {
        BaseModel ret = new BaseModel();
		
		Event evt = eventDao.read(eventId);
		
		if(evt == null){
			ret.setCode(INTERNAL_SERVER_ERROR.getStatusCode());
			ret.setStatus(INTERNAL_SERVER_ERROR.toString());
			ret.setMessage("no_such_event");
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("eventId", eventId);
			return ResponseUtil.buildResponse(ret);
		}
		
		User user = userDao.read(userId);
		if(user == null){
			ret.setCode(INTERNAL_SERVER_ERROR.getStatusCode());
			ret.setStatus(INTERNAL_SERVER_ERROR.toString());
			ret.setMessage("no_such_user");
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("userId", userId);
			return ResponseUtil.buildResponse(ret);
		}
		
		if(!evt.getAttendees().contains(user)){
			ret.setCode(INTERNAL_SERVER_ERROR.getStatusCode());
			ret.setStatus(INTERNAL_SERVER_ERROR.toString());
			ret.setMessage("user_not_in_attendees.");
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("userId", userId);
			data.put("eventId", eventId);
			return ResponseUtil.buildResponse(ret);
		}
		
		evt.getAttendees().remove(user);
		eventDao.update(evt);
		
		ret.setCode(OK.getStatusCode());
		ret.setStatus(OK.toString());
		ret.setMessage("success");
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("userId", userId);
		data.put("eventId", eventId);
		
		return ResponseUtil.buildResponse(ret);
	}

	@Override
	public Response updateSponser(String eventId, String userId) {
		BaseModel ret = new BaseModel();
		
		Event evt = eventDao.read(eventId);
		
		if(evt == null){
			ret.setCode(INTERNAL_SERVER_ERROR.getStatusCode());
			ret.setStatus(INTERNAL_SERVER_ERROR.toString());
			ret.setMessage("no_such_event");
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("eventId", eventId);
			return ResponseUtil.buildResponse(ret);
		}
		
		User user = userDao.read(userId);
		if(user == null){
			ret.setCode(INTERNAL_SERVER_ERROR.getStatusCode());
			ret.setStatus(INTERNAL_SERVER_ERROR.toString());
			ret.setMessage("no_such_user");
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("userId", userId);
			return ResponseUtil.buildResponse(ret);
		}
		
		if(evt.getOwner().equals(user)){
			ret.setCode(INTERNAL_SERVER_ERROR.getStatusCode());
			ret.setStatus(INTERNAL_SERVER_ERROR.toString());
			ret.setMessage("cannot_use_yourself");
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("userId", userId);
			data.put("eventId", eventId);
			return ResponseUtil.buildResponse(ret);
		}
		
		evt.setOwner(user);
		eventDao.update(evt);
		
		ret.setCode(OK.getStatusCode());
		ret.setStatus(OK.toString());
		ret.setMessage("success");
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("userId", userId);
		data.put("eventId", eventId);
		
		return ResponseUtil.buildResponse(ret);
	}

	@Override
	public Response publishMessage(String token, String eventId, MessageRequest request) {
		User user = userDao.getUserByToken(token);
		EventMessage msg = new EventMessage();
		msg.setUserId(user.getId());
		msg.setEventId(eventId);
		msg.setContent(request.getContent());
		msg.setPublishTime(new Date());
		msg.setType(MessageType.USER_MESSAGE);
		msg = messageDataService.create(msg );
		return ResponseUtil.buildResponse(msg);
	}

	@Override
	public Response getMessageListByEventId(String eventId,int offset,int limit) {		
		return ResponseUtil.buildResponse(messageDataService.findByEventId(eventId, offset, limit));
	}

	@Override
	public Response comment(String token, String eventId,
			CommentRequest request) {
		User user = userDao.getUserByToken(token);
		EventComment comment = new EventComment();
		comment.setContent(request.getContent());
		comment.setEventId(eventId); 
		comment.setUserId(user.getId());
		comment.setPublishTime(new Date());
		return ResponseUtil.buildResponse(commentDao.create(comment));
	}

	@Override
	public Response getCommentListByEventId(String eventId,
			int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
