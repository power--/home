package com.goparty.data.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goparty.data.model.OAuthAccessToken;

public class OAuthTokenTest  extends AbstractRepositoryTest{
	@Autowired
	private OAuthTokenDataService oAuthTokenDataService;
	
	@Test
	public void test(){
		List<OAuthAccessToken> list = oAuthTokenDataService.list();
		
		for(OAuthAccessToken t:list){
			System.out.println(t);
		}
	}
}
