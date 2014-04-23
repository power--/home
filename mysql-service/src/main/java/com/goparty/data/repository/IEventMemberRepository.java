package com.goparty.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.EventMember;
import com.goparty.data.model.EventMemberId;

@Transactional
public interface IEventMemberRepository extends JpaRepository<EventMember, EventMemberId>{ 
}
