package com.goparty.data.mongodb;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 

import com.goparty.data.model.UserData;
import com.goparty.data.service.UserDataService; 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/test.xml")
public class UserServiceTest {
	
	@Autowired
	UserDataService userDataService;
	
	@Test
	public void test(){
		 
		List<UserData> list = userDataService.getUserDataByNickName("Bo");
		for(UserData user : list){
			System.out.println(user.getId());
		}
	}

}
