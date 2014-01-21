package com.goparty.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
 

import com.goparty.data.model.UserFriend; 

@Transactional
public interface IUserFriendDataRepository extends MongoRepository<UserFriend, String>{

}
