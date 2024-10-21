package com.ved_api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ved_api.entity.User;
import com.ved_api.responseTemplate.ResponseTemplate;
import com.ved_api.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        String token = authService.signup(user);
        
        Map<String, String> data = new HashMap<>();
        data.put("email", user.getEmail());
        data.put("token", "Bearer " + token);
        
        ResponseTemplate<Map<String,String>> response = new ResponseTemplate<>(
                "success",
                "User registered successfully.",
                data
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        String token = authService.login(email, password);
        Map<String, String> data = new HashMap<>();
        data.put("email", email);
        data.put("token", "Bearer " + token);
        
        ResponseTemplate<Map<String, String>> response = new ResponseTemplate<>(
            "success",
            "Login successful.",
            data
        );
        
        return ResponseEntity.ok(response);
    }

}
