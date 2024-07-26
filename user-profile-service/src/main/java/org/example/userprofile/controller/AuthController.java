package org.example.userprofile.controller;

import lombok.RequiredArgsConstructor;
import org.example.userprofile.dto.request.UserProfileRequest;
import org.example.userprofile.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    @PostMapping("/userCreate")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody UserProfileRequest userProfileRequest) throws Exception {
        authService.register(userProfileRequest);
        return "User profile created!";
    }


}
