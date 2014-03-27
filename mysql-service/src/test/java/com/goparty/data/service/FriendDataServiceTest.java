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
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.goparty.data.model.Event;
import com.goparty.data.model.Group;
import com.goparty.data.model.User;
import com.goparty.data.model.UserFriend;
import com.goparty.data.model.UserFriendPK;
 

public class FriendDataServiceTest extends AbstractRepositoryTest {

	@Autowired
	private FriendDataService friendDataService;
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	@Test 
	public void testGroup(){
		Group g = new Group();
		g.setName("Girls");
		g.setOwnerId("33");
		g = friendDataService.addGroup(g);
		
		g.setName("Boys");
		friendDataService.updateGroup(g);
		
		friendDataService.deleteGroup(g.getId());
	}
	
	@Test
	public void testJdbc(){
		List<Group> list = jdbcTemplate.query("select * from gp_group",ParameterizedBeanPropertyRowMapper.newInstance(Group.class));
		for(Group g : list){
			System.out.println(g);
		}
		
	}
	
	@Test
	public void testGetFriends(){ 
		List<Group> list = jdbcTemplate.query("select * from gp_group",ParameterizedBeanPropertyRowMapper.newInstance(Group.class));
		friendDataService.getFriends("98", 0, 5);
		
		
	}

	@Test
	public void testCreate(){
		UserFriend uf = new UserFriend();
		uf.setUserId("33");
		uf.setFriendId("92");
		uf.setStatus("INIT");
		uf.setUpdateTime(new Date());		
		List<Group> groups = new ArrayList<Group>();
		Group g = new Group();
		g.setId("2");
		groups.add(g);
		Group g2 = new Group();
		g2.setId("3");
		groups.add(g2);
		uf.setGroups(groups);
		
		friendDataService.create(uf);
		
		uf.setStatus("AGREE");
		uf.setRemarkName("Jim");
		friendDataService.update(uf);
	}
 
	@Test
	public void testDelete(){
		UserFriendPK pk = new UserFriendPK();
		pk.setUserId("22");
		pk.setFriendId("92");
		friendDataService.delete(pk);
		
	}
	 
 
	
}

