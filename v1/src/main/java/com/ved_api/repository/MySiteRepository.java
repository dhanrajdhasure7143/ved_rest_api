package com.ved_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.ved_api.entity.MySite;

public interface MySiteRepository extends MongoRepository<MySite, String> {

}
