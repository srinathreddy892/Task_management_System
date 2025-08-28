package com.teluguskillhub.taskproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teluguskillhub.taskproject.payload.LoginDto;
import com.teluguskillhub.taskproject.payload.UserDto;
import com.teluguskillhub.taskproject.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    //  new user
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);

        // Hide password before sending response
        savedUser.setPassword(null);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully!");
        response.put("user", savedUser);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //  Login user
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User logged in successfully!");
        response.put("email", loginDto.getEmail());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
