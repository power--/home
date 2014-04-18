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
import com.goparty.data.repository.IEventRepository;


@Repository("eventDao")
@Transactional
public class EventDao {
	private Log log = LogFactory.getLog(EventDao.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private IEventRepository eventRepository;
	
	
	public Event read(String id) {
		Event event = eventRepository.findOne(id);
		return event;
	}

	public Event create(Event event) {
		return eventRepository.save(event);
	}

	public Event update(Event event) {
		return eventRepository.save(event);
		
	}

	public boolean delete(String id) {
		boolean ret = false;
		
		try{
			eventRepository.delete(id);
			ret = true;
		}catch(Exception ex){
			log.error("del user error",ex);
			throw ex;
		}		
		return ret;
	}

	public List<Event> getEvents(String userId,String scope, Date after, Date before,
			String categories, String search, long offset, long limit){
		if (limit ==0){
			limit = 30;
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("select ge.* from gp_event ge  ");
		if ("friends".equals(scope)){
			sb.append(" exists (select 1 from gp_user_friend guf where ge.ownerId = guf.friendId and guf.userId =:userId and ge.visibility = 'V_FRIEND')  ");
		} else if ("all".equals(scope)){
			sb.append(" ge.visibility = 'V_PUBLIC'  ");
		} else {
			sb.append(" exists (select 1 from gp_event_attendee gea where ge.event_id = gea.event_id and gae.userId =:userId)  ");
		} 
		sb.append("where ge.start_time>=after and ge.end_time<=before ");
		sb.append("limit " + offset + "," + limit);
		Query  query = em.createNativeQuery(sb.toString(), Event.class);

		if ("friends".equals(scope)){
			query.setParameter("userId", userId);
		} else if ("all".equals(scope)){
			//
		} else {
			query.setParameter("userId", userId);
		} 
		return query.getResultList();
	}	
	
}
