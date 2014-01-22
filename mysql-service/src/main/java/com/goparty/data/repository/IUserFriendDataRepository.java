package com.goparty.data.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
 


import com.goparty.data.model.UserFriend; 

@Transactional
public interface IUserFriendDataRepository extends JpaRepository<UserFriend, String>{

}
