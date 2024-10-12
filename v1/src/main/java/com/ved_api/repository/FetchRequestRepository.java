package com.ved_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ved_api.entity.FetchRequest;

@Repository
public interface FetchRequestRepository extends MongoRepository<FetchRequest, String> {

}
