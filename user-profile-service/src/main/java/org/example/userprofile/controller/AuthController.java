package org.example.userprofile.controller;

import lombok.RequiredArgsConstructor;

import org.example.userprofile.dto.request.UserLoginRequest;
import org.example.userprofile.dto.request.UserRegisterRequest;
import org.example.userprofile.dto.response.TokenResponse;
import org.example.userprofile.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        authService.register(userRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User profile created!");
    }

    @GetMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserLoginRequest userLoginRequest) throws Exception {
        String login = authService.login(userLoginRequest);
        return ResponseEntity.ok(new TokenResponse(login));
    }
}
