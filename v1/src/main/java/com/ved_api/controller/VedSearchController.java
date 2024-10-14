package com.ved_api.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ved_api.entity.FetchRequest;
import com.ved_api.service.VedSearchService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/v1/search")
public class VedSearchController {
	
    private static final Logger logger = LoggerFactory.getLogger(VedSearchController.class);
	
	@Autowired
    private VedSearchService vedSearchService;

    @PostMapping("/prompt")
    public ResponseEntity<?> scrapeGoogle(@RequestBody FetchRequest fetchRequest) throws IOException {
        String content = fetchRequest.getContent();
        logger.info("Received search request for content: {}", content);

        // Pass the content to the service to perform scraping and save result
		FetchRequest savedRequest = vedSearchService.fetchGeminiContent(content);
		logger.info("Search results saved successfully for content: {}", savedRequest.toString());
		return ResponseEntity.ok(savedRequest);
    }
}
