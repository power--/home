package com.goparty.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.biz.model.Event;
import com.goparty.biz.model.EventCategory;
import com.goparty.biz.model.EventStatus;
import com.goparty.biz.model.User;
import com.goparty.biz.model.VisiblityCategory;
import com.goparty.data.model.EventData;
import com.goparty.data.model.UserData;
import com.goparty.repository.IEventDataRepository;
import com.goparty.repository.IUserDataRepository;

@Repository("eventDataService")
@Transactional
public class EventDataService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IEventDataRepository eventDataRepository;
	@Autowired
	private IUserDataRepository userDataRepository;
	
	public Event create(Event event){
		try{
			String id = UUID.randomUUID().toString();
			event.setId(id);
			
			EventData data = new EventData();
			
			data.setId(event.getId());
			data.setTitle(event.getTitle());
			data.setStartTime(event.getStartTime());
			data.setEndTime(event.getEndTime());
			
			if(event.getAttendees()!=null&&event.getAttendees().size()>0){
				List<String> attendees = new ArrayList<String>();
				for(User u : event.getAttendees()){
					attendees.add(u.getId());
				}
				
				data.setAttendeeIds(attendees);
			}
			
			if(event.getOwner()!=null){
				data.setOwnerId(event.getOwner().getId());
			}
			
			data.setDescription(event.getDescription());
			
			if(event.getEventCategory()!=null){
				data.setEventCategoryId(event.getEventCategory().getId());
			}
			
			if(event.getEventStatus()!=null){
				data.setEventStatusId(event.getEventStatus().getId());
			}
			
			if((event.getVisiblityCategory()!=null)){
				data.setVisiblityCategoryId(event.getVisiblityCategory().getId());
			}
			
			eventDataRepository.save(data);
		}catch(Exception ex){
			logger.error("error happended to add a user",ex);
			throw ex;
		}
		
		return event;
	}
	
	public Event update(Event event){
		try{
			EventData data = new EventData();
			
			data.setId(event.getId());
			data.setTitle(event.getTitle());
			data.setStartTime(event.getStartTime());
			data.setEndTime(event.getEndTime());
			
			if(event.getAttendees()!=null&&event.getAttendees().size()>0){
				List<String> attendees = new ArrayList<String>();
				for(User u : event.getAttendees()){
					attendees.add(u.getId());
				}
				
				data.setAttendeeIds(attendees);
			}
			
			if(event.getOwner()!=null){
				data.setOwnerId(event.getOwner().getId());
			}
			
			data.setDescription(event.getDescription());
			
			if(event.getEventCategory()!=null){
				data.setEventCategoryId(event.getEventCategory().getId());
			}
			
			if(event.getEventStatus()!=null){
				data.setEventStatusId(event.getEventStatus().getId());
			}
			
			if((event.getVisiblityCategory()!=null)){
				data.setVisiblityCategoryId(event.getVisiblityCategory().getId());
			}
			
			eventDataRepository.save(data);
		}catch(Exception ex){
			logger.error("error happended to add a user",ex);
			throw ex;
		}
		
		return event;
	}
	
	public Event read(String id){
		Event data = null;
		try{
			
			EventData event = eventDataRepository.findOne(id);
			data.setId(event.getId());
			data.setTitle(event.getTitle());
			data.setStartTime(event.getStartTime());
			data.setEndTime(event.getEndTime());
			
			if(event.getAttendeeIds()!=null&&event.getAttendeeIds().size()>0){
				List<User> attendees = new ArrayList<User>();
				
				for(String userId : event.getAttendeeIds()){
					
					UserData userData = userDataRepository.findOne(id);
					
					if(userData != null){
						User user = this.toUser(userData);
					}
				}
				
				data.setAttendees(attendees);
			}
			
			if(event.getOwnerId()!=null){
				UserData userData = userDataRepository.findOne(event.getOwnerId());
				data.setOwner(this.toUser(userData));
			}
			
			data.setDescription(event.getDescription());
			
			if(event.getEventCategoryId()!=null){
				EventCategory eventCategory = new EventCategory();
				eventCategory.setId(event.getEventCategoryId());
				eventCategory.setName("Not implemented yet");
				data.setEventCategory(eventCategory);
			}
			
			if(event.getEventStatusId()!=null){
				EventStatus status = new EventStatus();
				status.setId(event.getEventStatusId());
				status.setName("Not implemented yet");
				data.setEventStatus(status);
			}
			
			if((event.getVisiblityCategoryId()!=null)){
				VisiblityCategory vc = new VisiblityCategory();
				vc.setName("Not implemented yet");
				vc.setId(event.getVisiblityCategoryId());
				data.setVisiblityCategory(vc);
			}
			
			
		}catch(Exception ex){
			logger.error("error happended to add a user",ex);
			throw ex;
		}
		
		return data;
	}
	
	private User toUser(UserData userData){
		User user = null;
		if(userData != null){
			user = new User();
			user = new User();
			user.setId(userData.getId());
			user.setUserName(userData.getUserName());
			user.setPassword(userData.getPassword());
			user.setNickName(userData.getNickName());
			user.setPhone(userData.getPhone());
			user.setBirthdate(userData.getBirthdate());
			user.setGender(userData.getGender());
			user.setLocation(userData.getLocation());
			user.setSignature(userData.getSignature());
			user.setPhoto(userData.getPhoto());
			user.setWeChat(userData.getWeChat());
			user.setQQ(userData.getQQ());
		}
		
		return user;
	}
	
	public boolean delete(String id){
		boolean ret = false;
		
		try{
			userDataRepository.delete(id);
			ret = true;
		}catch(Exception ex){
			logger.error("del user error",ex);
			throw ex;
		}
		
		return ret;
	}
}
