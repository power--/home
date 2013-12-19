package com.goparty.webservice.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.goparty.biz.model.*;
import com.goparty.webservice.EventService;
import com.party.data.service.EventDataService;

@Service("eventService")
public class EventServiceImpl implements EventService {
	@Autowired
	private EventDataService eventDataService;

	@Override
	public Event getEvent(String id) {
		Event ret = eventDataService.read(id);
		return ret;
	}

	@Override
	public Event addEvent(Event event) {
		eventDataService.create(event);
		return event;
	}

	@Override
	public Event updateUser(Event event) {
		eventDataService.update(event);
		return event;
	}

	@Override
	public boolean deleteEvent(String id) {
		boolean ret = false;
		try{
			eventDataService.delete(id);
			ret = true;
		}catch(Exception ex){
			throw ex;
		}
		return ret;
	}
}
