package com.goparty.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.OAuthAccessToken;
import com.goparty.data.repository.IOAuthAccessTokenRepository;

@Repository("OAuthTokenDataService")
@Transactional
public class OAuthTokenDataService {
	@Autowired
	private IOAuthAccessTokenRepository OAuthAccessTokenRepository;
	
	public List<OAuthAccessToken> list(){
		return OAuthAccessTokenRepository.findAll();
	}
}
