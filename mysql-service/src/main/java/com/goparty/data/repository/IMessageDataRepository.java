package com.goparty.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.EventMessage;
import com.goparty.data.model.UserFriend;
import com.goparty.data.model.UserFriendPK;
 

@Transactional
public interface IMessageDataRepository extends JpaRepository<EventMessage, String>{  
}
