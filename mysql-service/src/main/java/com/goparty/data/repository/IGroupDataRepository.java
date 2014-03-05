package com.goparty.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.Group; 
 

@Transactional
public interface IGroupDataRepository extends JpaRepository<Group, String>{ 
	public List<Group> findByOwnerId(String userId); 
}
