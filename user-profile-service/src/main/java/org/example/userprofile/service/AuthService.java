package org.example.userprofile.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.userprofile.dto.request.UserLoginRequest;
import org.example.userprofile.dto.request.UserRegisterRequest;
import org.example.userprofile.dto.response.TokenResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserProfileService userService;

    public void register(UserRegisterRequest userRegisterRequest) throws Exception {
        if(userService.isEmailExisting(userRegisterRequest.getEmail()) ||
                userService.isUsernameExisting(userRegisterRequest.getUsername())) {
            throw new Exception();
        }
        userService.createUserProfile(userRegisterRequest);
    }

    public TokenResponse login(UserLoginRequest userLoginRequest) {
        return null;
    }
}
