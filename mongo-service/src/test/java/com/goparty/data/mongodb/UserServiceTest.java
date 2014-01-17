package com.goparty.data.mongodb;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goparty.data.model.UserData;
import com.goparty.data.service.UserDataService; 


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/test.xml")
public class UserServiceTest {
	
	@Autowired
	UserDataService userDataService;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@SuppressWarnings("static-access")
	@Test
	public void test(){		 
		List<UserData> list = userDataService.findByNickNameLike("Bo",0,3);
		for(UserData user : list){
			System.out.println(user.getId());
		} 
		
		System.out.println("---------------another method to query------------");
		
		Criteria crit = new Criteria();
		crit = crit.where("nickName").is("Bo");
		Query query = new Query(crit); 
		List<UserData> userList = mongoTemplate.find(query, UserData.class);
		for(UserData user : userList){
			System.out.println(user.getId());
		} 
	}

}
