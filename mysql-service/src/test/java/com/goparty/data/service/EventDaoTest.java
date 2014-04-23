package com.goparty.data.service;
 

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.constant.EventStatus;
import com.goparty.data.constant.EventVisibility;
import com.goparty.data.dao.EventDao;
import com.goparty.data.model.Category;
import com.goparty.data.model.Event;
import com.goparty.data.model.User;
 

public class EventDaoTest extends AbstractRepositoryTest {

	@Autowired
	private EventDao eventDao;
	 
	@Test
	@Transactional
	public void test() {
//		Event event = eventDao.read("100");
		Event event = new Event();
		event.setDescription("Hello World");
		event.setTitle("A Title");
		event.setStatus(EventStatus.INIT);
		event.setVisibility(EventVisibility.V_FRIEND);
		
		User owner = new User();
		owner.setId("21");
		event.setOwner(owner);
		
		List<User> attendees = new ArrayList<User>();
		User user1 = new User();
		user1.setId("18"); 
		user1.setAdmin(true);
		User user2 = new User();
		user2.setId("19");  

		attendees.add(owner);
		attendees.add(user1);
		attendees.add(user2);		
		event.setMembers(attendees); 
		 
		
		Category cate = new Category();
		cate.setId("1");
		List<Category> cates = new ArrayList<Category>();
		cates.add(cate);
		event.setCategories(cates);		
		event = eventDao.create(event);
		System.out.println("event Id=" + event.getId());
		Event e = eventDao.read(event.getId());
		System.out.println("member size = " + e.getMembers().size() + "--" + e.getMembers().get(0).getId());
		e.setTitle("update Title" );
		eventDao.update(event);
		
		eventDao.delete(e.getId());
	} 
	 
	  
	
	@Test
	public void test2(){
		Event evt = new Event();
		evt.setTitle("hello");
		evt.setStartTime(new Date());
		evt.setEndTime(new Date());
		evt.setLocation("Shenzhen");
		evt.setDescription("test");
		Category cate = new Category();
		cate.setId("1"); 
		
		List<Category> cates = new ArrayList<Category>();
		cates.add(cate);
		evt.setCategories(cates);
		
		
		List<User> attendees = new ArrayList<User>();
		
		User u1 = new User();
		u1.setId("18");
		attendees.add(u1);
		
		
		User u2 = new User();
		u2.setId("19");
		attendees.add(u2);
		evt.setMembers(attendees);
		
		User owner = new User();
		owner.setId("18");
		evt.setOwner(owner);
		
		evt.setStatus(EventStatus.INIT);
		evt.setVisibility(EventVisibility.V_PUBLIC);
		evt = eventDao.create(evt);
	}
	
	@Test
	@Transactional
	public void testMany2ManyRead() throws JsonGenerationException, JsonMappingException, IOException{
		Event e = eventDao.read("34");	
		for(User u : e.getMembers()){
			System.out.println(e.getTitle() + " -- User:" + u.getId() + u.getNickName());
		}
		for(Category c : e.getCategories()){
			System.out.println(c.getName());
		}
	}
	
	@Test
	@Transactional
	public void testList() throws JsonGenerationException, JsonMappingException, IOException{
		List<Event> list = eventDao.getEvents("18", "all", null, null, "1|2", "hello|world", 0, 10);		
		for (Event event:list){
			System.out.println(event);
		}
	}
	
}
