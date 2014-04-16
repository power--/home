package com.goparty.data.service;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goparty.data.model.Event;
import com.goparty.data.model.Moment;
import com.goparty.data.model.Photo;
import com.goparty.data.model.User;
import com.goparty.data.repository.IMomentDataRepository;

public class MomentRepositoryTest extends AbstractRepositoryTest{
	
	@Autowired
	private IMomentDataRepository momentDataRepository;
	
	@Test
	public void test(){
		Moment model = new Moment();
		model.setId(UUID.randomUUID().toString());
		System.out.println("modelID:"+model.getId());
		
		User u = new User();
		u.setId("12");
		model.setUser(u);
		
		Event e = new Event();
		e.setId("1");
		model.setEvent(e);
		
		model.setMoment("111");
		
		Photo p = new Photo();
		p.setId(UUID.randomUUID().toString());
		System.out.println("photo:"+ p.getId());
		p.setMoment(model);
		p.setFormat("JPG");
		model.getPhotos().add(p);
		
		momentDataRepository.save(model);
		
	}
}
