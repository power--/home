package com.goparty.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.UserFriend;
import com.goparty.data.model.UserFriendPK;
 

@Transactional
public interface IFriendDataRepository extends JpaRepository<UserFriend, UserFriendPK>{ 
}
