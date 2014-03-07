package com.goparty.webservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		return eventCategoryDataService.list();
	}
}
