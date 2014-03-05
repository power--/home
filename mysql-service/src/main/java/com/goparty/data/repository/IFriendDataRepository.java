package com.goparty.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.UserFriend;
import com.goparty.data.model.UserFriendPK;
 

@Transactional
public interface IFriendDataRepository extends JpaRepository<UserFriend, UserFriendPK>{ 
	List<UserFriend> findByUserIdAndStatus(String userId,String status, Pageable pageable); 
}
