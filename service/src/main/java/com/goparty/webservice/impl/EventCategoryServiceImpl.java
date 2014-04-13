package com.goparty.webservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.goparty.data.model.EventCategory;
import com.goparty.data.service.EventCategoryDataService;
import com.goparty.webservice.EventCategoryService;

@Service("eventCategoryService")
public class EventCategoryServiceImpl implements EventCategoryService {
	@Autowired
	private EventCategoryDataService eventCategoryDataService;

	@Override
	public List<EventCategory> list() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    
	    for(int i=0;i<10;i++){
	    	System.out.println(name);
	    }
	      
		return eventCategoryDataService.list();
	}
}
