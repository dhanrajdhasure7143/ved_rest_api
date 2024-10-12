package com.ved_api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "search_requests")
public class FetchRequest {
	@Id
    private String id;
    private String content;
    private String searchResults;
    
    public FetchRequest(String content, String searchResults) {
        this.content = content;
        this.searchResults = searchResults;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(String searchResults) {
        this.searchResults = searchResults;
    }
}
