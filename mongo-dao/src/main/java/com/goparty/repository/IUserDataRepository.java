package com.goparty.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.UserData;

@Transactional
public interface IUserDataRepository extends MongoRepository<UserData, String>{

}
