package com.goparty.data.service;
 

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired; 

import com.goparty.data.constant.MessageType;
import com.goparty.data.dao.MessageDao;
import com.goparty.data.model.EventMessage;
 

public class MessageDaoTest extends AbstractRepositoryTest {

	@Autowired
	private MessageDao messageDao;
	 
	
	@Test 
	public void test(){
//		EventMessage msg = new EventMessage();
//		msg.setContent("funk you ");
//		msg.setEventId("12");
//		msg.setType(MessageType.USER_MESSAGE);
//		msg.setUserId("33");
//		msg.setPublishTime(new Date());
//		messageDataService.create(msg);
//		
//		msg.setType(MessageType.SYSTEM_MESSAGE);
		messageDao.update("2","update message");
		
//		messageDataService.delete("2");
	}
	 
	@Test
	public void testList(){
		List<EventMessage> list = messageDao.findByEventId("12", 0, 5);
		assertTrue(list.size()>0);
	}
 
	
}

