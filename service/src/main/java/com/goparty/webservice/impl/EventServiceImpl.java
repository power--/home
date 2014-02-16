package com.goparty.webservice.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





import com.goparty.data.model.*;
import com.goparty.data.service.EventDataService;
import com.goparty.webservice.EventService;

@Service("eventService")
public class EventServiceImpl implements EventService {
	@Autowired
	private EventDataService eventDataService;

	@Override
	public Event read(String id) {
		Event ret = eventDataService.read(id);
		return ret;
	}

	@Override
	public Event create(Event event) {
		eventDataService.create(event);
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


}
