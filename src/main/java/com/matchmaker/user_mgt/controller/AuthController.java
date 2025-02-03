package com.matchmaker.user_mgt.controller;

import com.matchmaker.user_mgt.model.User;
import com.matchmaker.user_mgt.model.dto.AuthenticationRequest;
import com.matchmaker.user_mgt.model.dto.AuthenticationResponse;
import com.matchmaker.user_mgt.model.dto.UserRequest;
import com.matchmaker.user_mgt.service.AuthenticationService;
import com.matchmaker.user_mgt.service.JwtService;
import com.matchmaker.user_mgt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;


    private final UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserRequest UserRequest) {
        User createdUser = userService.createUser(UserRequest);
        var jwtToken = jwtService.generateToken(createdUser);
        return new ResponseEntity<>(jwtToken, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
