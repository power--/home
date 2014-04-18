package com.goparty.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.Moment;

@Transactional
public interface IMomentRepository extends JpaRepository<Moment, String> {
	List<Moment>  findByEventId(String eventId, Pageable pageable);
}
