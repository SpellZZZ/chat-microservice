package org.example.jwtservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/jwt")
@RequiredArgsConstructor
public class JwtController {



    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String userProfile() {
        return "Welcome to User Profile";
    }



}
