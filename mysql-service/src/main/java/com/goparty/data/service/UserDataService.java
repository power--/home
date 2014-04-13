package com.goparty.data.service;

import java.util.Calendar;
import java.util.Date;
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
import com.goparty.data.repository.ITokenDataRepository;
import com.goparty.data.repository.IUserProfileDataRepository;
 

@Repository("userDataService")
@Transactional
public class UserDataService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private IUserProfileDataRepository userDataRepository;
	
	@Autowired
	private IEventDataRepository eventDataRepository;
	
	@Autowired
	private ITokenDataRepository tokenDataRepository;
	
	public List<UserProfile> search(String keyword,int page,int size){
		String sql = "select * from gp_user where loginId like '%keyword%' "
		 		+ "or nickName like '%keyword%' or qq like '%keyword%' or weChat like '%keyword%' "
		 		+ "or phone like '%keyword%' limit " + page + "," + size;
		 Query query = em.createNativeQuery(sql.replace("keyword", keyword) , UserProfile.class);		 
		List<UserProfile> list = query.getResultList();
		return list;
	}
	
	
	public UserProfile create(UserProfile user){	 
		return userDataRepository.save(user); 
	}
	
	public UserProfile update(UserProfile user){ 
		return userDataRepository.save(user);
	}
	
	public UserProfile read(String id){
		UserProfile user = userDataRepository.findOne(id);		
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
	
	public List<Event> findByEventCategoryId(String cateId,int offset,int limit){		
		PageRequest pageable = new PageRequest(offset, limit);		 
		Page<Event> events = eventDataRepository.findByEventCategoryIdOrderByStartTimeDesc(cateId, pageable);
		return events.getContent();
	}
	
	public UserProfile getUserByLoginId(String loginId){
		return userDataRepository.findByLoginId(loginId);
	}
	
	public UserProfile getUserByToken(String token){
		UserProfile user = null;
		UserToken ut = tokenDataRepository.findByToken(token);
		if(ut!=null){
			user = userDataRepository.findOne(ut.getUserId());	
			user.setToken(ut.getToken());
		}
		return 	user;
	}
	
	
	public UserToken getUserToken(String token){
		return tokenDataRepository.findByToken(token);
	}
	
	public UserToken generateToken(String userId, int offsetDays){
		UserToken token = new UserToken();
		token.setUserId(userId);
		token.setToken(UUID.randomUUID().toString());
		token.setApplyTime(new Date());
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_YEAR, offsetDays);//one month
		token.setExpireTime(cal.getTime());
		UserToken ut = tokenDataRepository.save(token);	
		return ut;
	}
}
