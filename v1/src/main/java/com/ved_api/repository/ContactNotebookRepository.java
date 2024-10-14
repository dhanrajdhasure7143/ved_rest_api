package com.ved_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ved_api.entity.ContactNotebook;

@Repository
public interface ContactNotebookRepository extends MongoRepository<ContactNotebook, Integer> {

}
