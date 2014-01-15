package com.goparty.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
 
import com.goparty.data.model.UserFriendData;

@Transactional
public interface IUserFriendDataRepository extends MongoRepository<UserFriendData, String>{

}
