package com.goparty.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.UserProfile; 

@Transactional
public interface IUserProfileDataRepository extends JpaRepository<UserProfile, String>{ 
	public UserProfile findByLoginId(String loginId);
}
