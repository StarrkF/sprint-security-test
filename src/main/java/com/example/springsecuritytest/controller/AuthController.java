package com.example.springsecuritytest.controller;

import com.example.springsecuritytest.dto.response.AuthResponse;
import com.example.springsecuritytest.dto.request.LoginRequest;
import com.example.springsecuritytest.dto.request.RegisterRequest;
import com.example.springsecuritytest.model.User;
import com.example.springsecuritytest.security.JwtTokenUtil;
import com.example.springsecuritytest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        if (userService.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        if (userService.existByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already in use!");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userService.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();
        String jwt = jwtTokenUtil.generateToken(user);

        return ResponseEntity.ok(new AuthResponse(jwt, user.getUsername(), user.getEmail(), user.getAuthorities()));
    }
}
