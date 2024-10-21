package com.ved_api.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ved_api.entity.Role;
import com.ved_api.entity.User;
import com.ved_api.repository.UserRepository;
import com.ved_api.util.JwtTokenUtil;

@Service
public class AuthService {
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    public String signup(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(Role.USER)); // Default role
        userRepository.save(user);
        return jwtTokenUtil.generateToken(user.getEmail());
    }

    public String login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty() || !passwordEncoder.matches(password, userOptional.get().getPassword())) {
            throw new RuntimeException("Invalid credentials.");
        }
        return jwtTokenUtil.generateToken(email);
    }
}
