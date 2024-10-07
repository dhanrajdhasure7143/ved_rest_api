package com.ved_api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ved_api.entity.MySite;
import com.ved_api.repository.MySiteRepository;

@Service
public class MySiteService {

	@Autowired
	private MySiteRepository mySiteRepository;
	
	public List<MySite> getAllSites() {
        return mySiteRepository.findAll();
    }

    public Optional<MySite> getSiteById(String site_id) {
        return mySiteRepository.findById(site_id);
    }

    public MySite addSite(MySite site) {
        return mySiteRepository.save(site);
    }

    public void deleteSite(String site_id) {
        mySiteRepository.deleteById(site_id);
    }
}
