package com.goparty.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.User;

@Transactional
public interface IUserDataRepository extends  JpaRepository<User, Integer> {
	User findByUsername(String username);
}
