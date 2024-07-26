package org.example.userprofile.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.userprofile.dto.request.UserProfileRequest;
import org.example.userprofile.dto.response.response.UserProfileResponse;
import org.example.userprofile.model.UserProfile;
import org.example.userprofile.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserProfileService {

    private final UserRepo userRepo;

    public void createUserProfile(UserProfileRequest userProfileRequest) {

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


        return  UserProfileResponse.builder()
                    .id(user.getUserId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .build();


    }
}
