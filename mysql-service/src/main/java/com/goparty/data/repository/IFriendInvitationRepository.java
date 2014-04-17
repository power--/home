package com.goparty.data.repository;
 
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.FriendInvitation; 
import com.goparty.data.model.UserFriend;
 

@Transactional
public interface IFriendInvitationRepository extends JpaRepository<FriendInvitation, String>{  

	//获取被邀请者的信息
	List<FriendInvitation> findByinviteeIdAndStatus(String inviteeId,String status, Pageable pageable); 
	
	//获取邀请者的信息
	List<FriendInvitation> findByinviterIdAndStatus(String inviterId,String status, Pageable pageable); 
	
}
