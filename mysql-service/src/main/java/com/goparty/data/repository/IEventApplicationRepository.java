package com.goparty.data.repository;
 
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.EventApplication;
 

@Transactional
public interface IEventApplicationRepository extends JpaRepository<EventApplication, String>{  

	//获取被邀请者的信息
	List<EventApplication> findByapproverIdAndStatus(String approverId,String status, Pageable pageable); 
	
	//获取邀请者的信息
	List<EventApplication> findByapplicantIdAndStatus(String applicantId,String status, Pageable pageable); 
	
}
