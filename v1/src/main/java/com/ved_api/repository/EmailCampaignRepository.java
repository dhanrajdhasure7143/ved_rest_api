package com.ved_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ved_api.entity.EmailCampaignRequest;

@Repository
public interface EmailCampaignRepository extends MongoRepository<EmailCampaignRequest, String> {

}
