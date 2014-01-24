package com.goparty.data.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.Event;
import com.goparty.data.model.User;
import com.goparty.data.repository.IEventDataRepository; 


@Repository("eventDataService")
@Transactional
public class EventDataService {
	private Log log = LogFactory.getLog(EventDataService.class);
	
	@Autowired
	private IEventDataRepository eventDataRepository;
	
	
	public Event read(String id) {
		Event event = eventDataRepository.findOne(id);
		return event;
	}

	public Event create(Event event) {
		return eventDataRepository.save(event);
	}

	public Event update(Event event) {
		return eventDataRepository.save(event);
		
	}

	public boolean delete(String id) {
		boolean ret = false;
		
		try{
			eventDataRepository.delete(id);
			ret = true;
		}catch(Exception ex){
			log.error("del user error",ex);
			throw ex;
		}		
		return ret;
	}
	
	
	public List<Event> findByEventCategoryId(String cateId,int page,int size){		
		PageRequest pageable = new PageRequest(page, size);		 
		Page<Event> events = eventDataRepository.findByEventCategoryIdOrderByStartTimeDesc(cateId, pageable);
		return events.getContent();
	}

}
