package com.goparty.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.User; 

@Transactional
public interface IUserDataRepository extends MongoRepository<User, String>{
	public Page<User> findByNickNameLike(String nickName, Pageable pageable);
}
