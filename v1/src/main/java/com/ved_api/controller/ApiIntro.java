package com.ved_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ved_api.responseTemplate.ResponseTemplate;

@RestController
@RequestMapping("/api/v1")
public class ApiIntro {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/info")
    public <T> ResponseTemplate<T> getApiIntroduction() {
        String message = "Welcome to the Email Campaigning API. This API allows users to create and manage email campaigns easily. "
                + "You can send emails using various providers by providing your credentials and the content you wish to send.";
//        New CHanges
        return new ResponseTemplate("Success", "API Info fetched Successfully", message);
    }
}
