package org.example.userprofile.controller;

import lombok.RequiredArgsConstructor;


import org.example.userprofile.dto.request.UserLoginRequest;
import org.example.userprofile.dto.request.UserRegisterRequest;
import org.example.userprofile.dto.response.TokenResponse;
import org.example.userprofile.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public Mono<ResponseEntity<String>> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        return authService.register(userRegisterRequest)
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).body("User profile created!")))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage())));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserLoginRequest userLoginRequest) throws Exception {

        TokenResponse login = authService.login(userLoginRequest);
        return ResponseEntity.ok(login);

    }


}
