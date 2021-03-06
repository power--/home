package com.goparty.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
 
import com.goparty.data.model.UserToken;
 

@Transactional
public interface ITokenRepository extends JpaRepository<UserToken, String>{ 
	public UserToken findByToken(String token); 
}
