package com.example.springsecuritytest.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthResponse {

    private String token;
    private String username;
    private String email;
    private Collection<? extends GrantedAuthority> roles;


    public AuthResponse() {
    }

    public AuthResponse(String token, String username, String email, Collection<? extends GrantedAuthority> roles) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }
}
