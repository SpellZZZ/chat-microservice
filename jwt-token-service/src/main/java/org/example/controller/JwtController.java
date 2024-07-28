package org.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.dto.request.AuthRequest;
import org.example.dto.response.TokenResponse;
import org.example.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/jwt")
@RestController
@RequiredArgsConstructor
public class JwtController {
    private final JwtService jwtService;


    @GetMapping("/getToken")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse getToken(@RequestBody AuthRequest authRequest) {
        return jwtService.getToken(authRequest);
    }

}
