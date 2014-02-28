package com.goparty.data.service;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.*;
import com.goparty.data.repository.IEventDataRepository;
import com.goparty.data.repository.IUserDataRepository;
 

@Repository("userDataService")
@Transactional
public class UserDataService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private IUserDataRepository userDataRepository;
	
	@Autowired
	private IEventDataRepository eventDataRepository;
	
	public List<User> search(String keyword,int page,int size){
		String sql = "select * from gp_user where loginId like '%keyword%' "
		 		+ "or nickName like '%keyword%' or qq like '%keyword%' or weChat like '%keyword%' "
		 		+ "or phone like '%keyword%' limit " + page + "," + size;
		 Query query = em.createNativeQuery(sql.replace("keyword", keyword) , User.class);		 
		List<User> list = query.getResultList();
		return list;
	}
	
	
	public User create(User user){	 
		return userDataRepository.save(user); 
	}
	
	public User update(User user){ 
		return userDataRepository.save(user);
	}
	
	public User read(String id){
		User user = userDataRepository.findOne(id);		
		return user;
	}
	
	public boolean delete(String id){
		boolean ret = false;
		
		try{
			userDataRepository.delete(id);
			ret = true;
		}catch(Exception ex){
			logger.error("del user error",ex);
			throw ex;
		}
		
		return ret;
	}
	
	public List<Event> findByEventCategoryId(String cateId,int page,int size){		
		PageRequest pageable = new PageRequest(page-1, size);		 
		Page<Event> events = eventDataRepository.findByEventCategoryIdOrderByStartTimeDesc(cateId, pageable);
		return events.getContent();
	}
}
