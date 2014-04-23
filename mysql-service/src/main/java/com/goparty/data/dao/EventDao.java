package com.goparty.data.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.constant.InvitationStatus;
import com.goparty.data.model.Event;
import com.goparty.data.model.EventApplication;
import com.goparty.data.model.EventInvitation;
import com.goparty.data.repository.IEventApplicationRepository;
import com.goparty.data.repository.IEventInvitationRepository;
import com.goparty.data.model.User;
import com.goparty.data.repository.IEventRepository;


@Repository("eventDao")
@Transactional
public class EventDao {
	private static Log log = LogFactory.getLog(EventDao.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private IEventRepository eventRepository;
	
	@Autowired
	private IEventInvitationRepository eventInvitationRepository;
	
	@Autowired
	private IEventApplicationRepository eventApplicationRepository;
	
	
	public Event read(String id) {
		Event event = eventRepository.findOne(id);
		return event;
	}

	public Event create(Event event) {
		 em.persist(event);
		 em.flush();
		 for(User u : event.getMembers()){
			 if(u.isAdmin()){
				 Query query = em.createNativeQuery("update gp_event_member set admin='Y' where eventId=? and userId=?");
				query.setParameter(1, event.getId());
				query.setParameter(2, u.getId());
				query.executeUpdate(); 
			 }
		 }		 
		 return event;
	}

	public Event update(Event event) {
		em.merge(event);
		return event;
		
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
			sb.append(" exists (select 1 from gp_event_member gea where ge.event_id = gea.event_id and gae.userId =:userId)  ");
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
	
	//invitation
	public void addInvitation(EventInvitation invitation){
		eventInvitationRepository.save(invitation);
	}
	public EventInvitation getInvitation(String invitationId){
		return eventInvitationRepository.findOne(invitationId);
	}
	public void updateInvitation(EventInvitation invitation){
		eventInvitationRepository.save(invitation);
	}
	
	//application
	public void addApplication(EventApplication application){
		eventApplicationRepository.save(application);
	}
	public EventApplication getApplication(String applicationId){
		return eventApplicationRepository.findOne(applicationId);
	}
	public void updateApplication(EventApplication application){
		eventApplicationRepository.save(application);
	}
		
	public List<EventInvitation> getUnrespondedInvitations(String inviteeId, long offset,
			long limit) {
		Pageable pageable = new PageRequest((int)offset,(int)limit); 
		return eventInvitationRepository.findByinviteeIdAndStatus(inviteeId, InvitationStatus.INIT, pageable);
	}

	public List<EventInvitation>  getRespondedInvitations(String inviteeId, long offset,
			long limit) {
		Pageable pageable = new PageRequest((int)offset,(int)limit); 
		return eventInvitationRepository.findByinviteeIdAndStatus(inviteeId, InvitationStatus.RESP, pageable);
	}

	public List<EventApplication>  getUnrespondedApplications(String approverId, long offset,
			long limit) {
		Pageable pageable = new PageRequest((int)offset,(int)limit); 
		return eventApplicationRepository.findByapproverIdAndStatus(approverId, InvitationStatus.INIT, pageable);
	}

	public List<EventApplication> getRespondedApplications(String approverId, long offset,
			long limit) {
		Pageable pageable = new PageRequest((int)offset,(int)limit); 
		return eventApplicationRepository.findByapproverIdAndStatus(approverId, InvitationStatus.RESP, pageable);
	}
	
}
