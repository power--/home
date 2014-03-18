package com.goparty.data.repository;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.EventComment; 
 

@Transactional
public interface ICommentDataRepository extends JpaRepository<EventComment, String>{  
	Page<EventComment> findByEventId(String eventId,Pageable pageable);
}
