package org.example.userprofile.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.userprofile.dto.request.UserRegisterRequest;
import org.example.userprofile.dto.response.UserProfileResponse;
import org.example.userprofile.model.UserProfile;
import org.example.userprofile.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserProfileService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public void createUserProfile(UserRegisterRequest userProfileRequest) {
        UserProfile userProfile = UserProfile.builder()
                .username(userProfileRequest.getUsername())
                .email(userProfileRequest.getEmail())
                .password(passwordEncoder.encode(userProfileRequest.getPassword()))
                .build();
        userRepo.save(userProfile);
    }


    public List<UserProfileResponse> getUserProfiles() {
        return userRepo.findAll().stream()
                .map(user ->
                        UserProfileResponse.builder()
                                .id(user.getUserId())
                                .username(user.getUsername())
                                .email(user.getEmail())
                                .build()
                ).toList();
    }


    public UserProfileResponse getUserProfile(Long userProfileId) {
        UserProfile user = userRepo.findById(userProfileId).orElse(null);
        if (user == null) {
            return null;
        }
        return UserProfileResponse.builder()
                .id(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }


    public boolean isEmailExisting(String email) {
        return userRepo.findUserProfileByEmail(email).isPresent();
    }

    public boolean isUsernameExisting(String username) {
        return userRepo.findUserProfileByUsername(username).isPresent();
    }

    public UserProfileResponse getUserProfileByUserName(String userName) {
        Optional<UserProfile> userOp = userRepo.findUserProfileByUsername(userName);
        if (userOp.isEmpty()) {
            return null;
        }
        UserProfile user = userOp.get();
        return UserProfileResponse.builder()
                .id(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}