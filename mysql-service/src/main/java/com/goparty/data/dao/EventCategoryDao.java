package com.goparty.data.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.EventCategory;
import com.goparty.data.repository.IEventCategoryRepository;

@Repository("eventCategoryDao")
@Transactional
public class EventCategoryDao {
	private Log log = LogFactory.getLog(EventCategoryDao.class);

	@Autowired
	private IEventCategoryRepository eventCategoryRepository;
	
	public List<EventCategory> list(){
		return eventCategoryRepository.findAll();
	}
}
