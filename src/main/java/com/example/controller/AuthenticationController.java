package com.example.controller;

import com.example.payload.request.LoginRequest;
import com.example.payload.response.AuthResponse;
import com.example.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    // ----------------------------LOGIN---------------------------------
    @PostMapping("/login")//http://localhost:8080/auth/login + POST +JSON @RequestBody @Valid

    public ResponseEntity<AuthResponse>authenticateUser(@RequestBody @Valid LoginRequest loginRequest){

        return authenticationService.authenticateUser(loginRequest);

    }







}
