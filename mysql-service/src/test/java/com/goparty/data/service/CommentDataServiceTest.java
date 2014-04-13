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
 
import com.goparty.data.model.EventComment;
 

public class CommentDataServiceTest extends AbstractRepositoryTest {

	@Autowired
	private CommentDataService commentDataService;
	 
	
	@Test 
	public void test(){
		EventComment comment = new EventComment();
		comment.setContent("funk you ");
		comment.setEventId("12"); 
		comment.setUserId("33");
		comment.setPublishTime(new Date());
		commentDataService.create(comment);
		 
		//commentDataService.update("2","update comment");
		
//		commentDataService.delete("2");
	}
	 
	@Test
	public void testList(){
		List<EventComment> list = commentDataService.findByEventId("12", 0, 5);
		assertTrue(list.size()>0);
	}
 
	
}

