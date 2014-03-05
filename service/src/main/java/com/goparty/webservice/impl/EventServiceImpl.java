package com.goparty.webservice.impl;



import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;









import com.goparty.data.model.*;
import com.goparty.data.service.EventDataService;
import com.goparty.ex.BaseException;
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
	public Event create(Event event) throws BaseException{
		if(event.getTitle()==null){
			throw new BaseException(Status.INTERNAL_SERVER_ERROR,"E10010","The event title should not be null");
		}
		
		System.out.println(event.getId());
		System.out.println(event.getId());
		eventDataService.create(event);
		
		User user = new User();
		user.setId("18");
		List<User> refusedAttendees = new ArrayList<User>();
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


}
