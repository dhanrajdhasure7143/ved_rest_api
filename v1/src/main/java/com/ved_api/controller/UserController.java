package com.ved_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ved_api.dto.UserDto;
import com.ved_api.entity.User;
import com.ved_api.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
    private UserRepository userRepository;
	
	 @GetMapping("/details")
	    public UserDto getUserDetails() {
	        // Get the current authenticated user
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String email = authentication.getName();

	        // Fetch user from the database
	        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

	        // Return user details
	        return new UserDto(user.getUsername(), user.getEmail(), user.getRoles());
	    }
}
