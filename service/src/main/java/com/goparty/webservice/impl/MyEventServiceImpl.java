package com.goparty.webservice.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goparty.data.model.Event;
import com.goparty.data.model.User;
import com.goparty.data.service.UserDataService;
import com.goparty.webservice.MyEventService;

@Service("myEventService")
public class MyEventServiceImpl implements MyEventService{

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UserDataService userDataService;
	
	@Override
	public List<Event> list(String token, Date after, Date before, String categories,
			String search, long offset, long limit) {
		
		List<Event> ret = null;
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		
		CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
		Root<Event> rootEvent = criteria.from(Event.class);
		criteria.select(rootEvent);
		
		
		User owner = userDataService.getUserByToken(token);
		
		
		
		criteria.where(builder.equal(rootEvent.get("owner"), owner));
		
		TypedQuery<Event> query = em.createQuery(criteria);
		
		ret = query.getResultList();
		
		for(Event e:ret){
			e.getOwner().setFriends(null);
		}
		return ret;
	}
}
