package com.goparty.data.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.Event;
import com.goparty.data.repository.IEventDataRepository;


@Repository("eventDao")
@Transactional
public class EventDao {
	private Log log = LogFactory.getLog(EventDao.class);
	
	@PersistenceContext
	private EntityManager em;
	
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

	public List<Event> getEvents(String userId,String scope, Date after, Date before,
			String categories, String search, long offset, long limit){
		StringBuffer sb = new StringBuffer();
		sb.append("select ge.* from gp_event ge where ge.start_time>=after and ge.end_time<=before");
		sb.append("limit " + offset + "," + limit);
		Query  query = em.createNativeQuery(sb.toString(), Event.class);
		return query.getResultList();
	}	
	
}
