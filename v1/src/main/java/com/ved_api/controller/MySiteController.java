package com.ved_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ved_api.entity.MySite;
import com.ved_api.service.MySiteService;

@RestController
@RequestMapping("/api/v1/sites")
public class MySiteController {
	
	@Autowired
	private MySiteService mySiteService;
	
	 // Get all sites
    @GetMapping
    public List<MySite> getAllSites() {
        return mySiteService.getAllSites();
    }

    // Get a site by ID
    @GetMapping("/{id}")
    public Optional<MySite> getSiteById(@PathVariable("id") String id) {
        return mySiteService.getSiteById(id);
    }

    // Add a new site
    @PostMapping
    public MySite addSite(@RequestBody MySite site) {
        return mySiteService.addSite(site);
    }

    // Delete a site by ID
    @DeleteMapping("/{id}")
    public void deleteSite(@PathVariable("id") String id) {
        mySiteService.deleteSite(id);
    }
	
}
