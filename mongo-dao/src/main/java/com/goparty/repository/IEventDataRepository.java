package com.goparty.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.EventData;

@Transactional
public interface IEventDataRepository extends MongoRepository<EventData, String>{

}
