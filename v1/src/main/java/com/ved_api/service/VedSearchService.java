package com.ved_api.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ved_api.entity.FetchRequest;
import com.ved_api.repository.FetchRequestRepository;

@Service
public class VedSearchService {

    private static final Logger logger = LoggerFactory.getLogger(VedSearchService.class);
    private static final String API_KEY = "AIzaSyBEXCwcvWO0nR1b9cojr-_9RV5dIRUnr88"; // Replace with your actual API key
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=" + API_KEY;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    FetchRequestRepository fetchRequestRepository;  // Injected FetchRequestRepository

    public FetchRequest fetchGeminiContent(String content) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Modified request body to match the JavaScript structure
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", Collections.singletonList(
            Map.of(
                "role", "user",
                "parts", Collections.singletonList(Map.of("text", content))
            )
        ));

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            logger.info("Sending request to Gemini API for content: {}", content);

            // Use ParameterizedTypeReference for type safety
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                API_URL,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<Map<String, Object>>() {}
            );

            logger.info("Response from Gemini API: {}", response.getBody());

            // Extract the content from the response
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("candidates")) {
                List<?> candidates = (List<?>) responseBody.get("candidates");
                if (!candidates.isEmpty() && candidates.get(0) instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> candidate = (Map<String, Object>) candidates.get(0);

                    if (candidate.containsKey("content") && candidate.get("content") instanceof Map) {
                        @SuppressWarnings("unchecked")
                        Map<String, Object> contentMap = (Map<String, Object>) candidate.get("content");

                        if (contentMap.containsKey("parts") && contentMap.get("parts") instanceof List) {
                            List<?> parts = (List<?>) contentMap.get("parts");

                            if (!parts.isEmpty() && parts.get(0) instanceof Map) {
                                @SuppressWarnings("unchecked")
                                Map<String, Object> partMap = (Map<String, Object>) parts.get(0);
                                String searchResults = (String) partMap.get("text");

                                logger.info("Extracted search results: {}", searchResults);

//                                searchResults = cleanText(searchResults);

                                // Save to MongoDB repository
                                FetchRequest fetchRequest = new FetchRequest(content, searchResults);
                                return fetchRequestRepository.save(fetchRequest);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error while calling Gemini API: {}", e.getMessage(), e);
        }

        // Return a fetch request with empty results if something goes wrong
        return fetchRequestRepository.save(new FetchRequest(content, ""));
    }
    
    @SuppressWarnings("unused")
	private String cleanText(String text) {
        text = text.replace("\n", " ");
        return text.replaceAll("<[^>]*>", "");
    }

}
