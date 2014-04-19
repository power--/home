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
import com.goparty.data.model.EventComment; 
import com.goparty.data.repository.ICommentRepository;
import com.goparty.data.repository.IUserRepository;
@Repository("commentDao")
@Transactional
public class CommentDao {
	
	private static Log log = LogFactory.getLog(CommentDao.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ICommentRepository commentRepository;
	
	 
	@Autowired
	private IUserRepository userRepository;
	 
	
	public EventComment read(String id) {
		EventComment comment = commentRepository.findOne(id);
		return comment;
	}

	public EventComment create(EventComment comment) { 		 
		comment = commentRepository.save(comment);
		return comment;
	}

	public boolean update(String id, String content) {	 
		EventComment comment = commentRepository.findOne(id);
		if(comment!=null){
			Query query = em.createNativeQuery("update gp_event_comment set content=:content where id=:id");
			query.setParameter("id", id);
			query.setParameter("content", content);
			query.executeUpdate();
		}else{
			throw new BaseException("comment not exist.");			
		}
		return true;		
	}

	public boolean delete(String id) {
		boolean ret = false;		
		try{
			commentRepository.delete(id);
			ret = true;
		}catch(Exception ex){
			log.error("del user error",ex);
			throw ex;
		}		
		return ret;
	}
	 
	public List<EventComment> findByEventId(String eventId,int offset,int limit){		
		PageRequest pageable = new PageRequest(offset, limit);		 
		Page<EventComment> commentList = commentRepository.findByEventId(eventId, pageable);
		return commentList.getContent();
	} 
}
