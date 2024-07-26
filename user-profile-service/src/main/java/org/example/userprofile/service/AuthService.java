package org.example.userprofile.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.userprofile.dto.request.UserProfileRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserProfileService userService;

    public void register(UserProfileRequest userProfileRequest) throws Exception {
        if(userService.isEmailExisting(userProfileRequest.getEmail()) ||
                userService.isUsernameExisting(userProfileRequest.getUsername())) {
            throw new Exception();
        }
        userService.createUserProfile(userProfileRequest);
    }
}
