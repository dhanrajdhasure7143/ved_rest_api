package com.ved_api.dto;

import java.util.Set;

import com.ved_api.entity.Role;

public class UserDto {
    private String username;
    private String email;
    private Set<Role> roles;

    // Constructor
    public UserDto(String username, String email, Set<Role> roles) {
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}

