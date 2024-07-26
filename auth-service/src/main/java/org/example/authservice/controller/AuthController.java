package org.example.authservice.controller;

import lombok.RequiredArgsConstructor;

import org.example.authservice.dao.request.UserLoginRequest;
import org.example.authservice.dao.request.UserRegisterRequest;
import org.example.authservice.dao.response.TokenResponse;
import org.example.authservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody UserRegisterRequest userRegisterRequest) throws Exception {
        authService.register(userRegisterRequest);
        return "User profile created!";
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserLoginRequest userLoginRequest) throws Exception {

        TokenResponse login = authService.login(userLoginRequest);
        return ResponseEntity.ok(login);

    }


}
