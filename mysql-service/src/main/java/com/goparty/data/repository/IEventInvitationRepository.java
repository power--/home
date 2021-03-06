package com.goparty.data.repository;
 
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.constant.InvitationStatus;
import com.goparty.data.model.EventInvitation;
 

@Transactional
public interface IEventInvitationRepository extends JpaRepository<EventInvitation, String>{  

	//获取被邀请者的信息
	List<EventInvitation> findByinviteeIdAndStatus(String inviteeId,InvitationStatus status, Pageable pageable); 
	
	//获取邀请者的信息
	List<EventInvitation> findByinviterIdAndStatus(String inviterId,InvitationStatus status, Pageable pageable); 
	
}
