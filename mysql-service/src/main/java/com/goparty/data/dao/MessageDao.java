package com.goparty.data.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.exception.BaseException;
import com.goparty.data.model.Event;
import com.goparty.data.model.EventMessage; 
import com.goparty.data.repository.IMessageRepository;
import com.goparty.data.repository.IUserRepository;
@Repository("messageDao")
@Transactional
public class MessageDao {
	
	private static Log log = LogFactory.getLog(MessageDao.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private IMessageRepository messageRepository;
	
	 
	@Autowired
	private IUserRepository userRepository;
	 
	
	public EventMessage read(String id) {
		EventMessage message = messageRepository.findOne(id);
		return message;
	}

	public EventMessage create(EventMessage msg) { 		 
		msg = messageRepository.save(msg);
		return msg;
	}

	public boolean update(String id, String content) {	 
		EventMessage updateMsg = messageRepository.findOne(id);
		if(updateMsg!=null){
			Query query = em.createNativeQuery("update gp_event_message set content=:content where id=:id");
			query.setParameter("id", id);
			query.setParameter("content", content);
			query.executeUpdate();
		}else{
			throw new BaseException("message not exist.");			
		}
		return true;		
	}

	public boolean delete(String id) {
		boolean ret = false;		
		try{
			messageRepository.delete(id);
			ret = true;
		}catch(Exception ex){
			log.error("del user error",ex);
			throw ex;
		}		
		return ret;
	}
	 
	public List<EventMessage> findByEventId(String eventId,int offset,int limit){		
		PageRequest pageable = new PageRequest(offset, limit);		 
		Page<EventMessage> msgList = messageRepository.findByEventId(eventId, pageable);
		return msgList.getContent();
	} 
}
