package org.example.service;


import lombok.RequiredArgsConstructor;
import org.example.dto.request.AuthRequest;
import org.example.dto.response.TokenResponse;
import org.example.util.JwtUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtUtil jwtUtil;


    public TokenResponse getToken(AuthRequest authRequest) {

        return TokenResponse.builder()
                        .token(jwtUtil.generateToken(authRequest))
                .build();
    }
}
