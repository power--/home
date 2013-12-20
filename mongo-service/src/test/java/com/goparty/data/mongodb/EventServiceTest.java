package com.goparty.data.mongodb;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goparty.data.model.EventData;
import com.goparty.repository.IEventDataRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/mongo.xml")
public class EventServiceTest {
	@Autowired
	private IEventDataRepository eventDataRepository;
	
	@Test
	public void testOne() throws Exception{
		EventData data = new EventData();
		String id = java.util.UUID.randomUUID().toString();
		data.setId(id);
		data.setDescription("test");
		
		eventDataRepository.save(data);
		Assert.assertEquals(1, eventDataRepository.count());
		eventDataRepository.delete(id);
		Assert.assertEquals(0, eventDataRepository.count());
	}
	
}
