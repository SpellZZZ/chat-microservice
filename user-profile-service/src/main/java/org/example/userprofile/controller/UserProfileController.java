package org.example.userprofile.controller;


import lombok.RequiredArgsConstructor;
import org.example.userprofile.dto.request.UserProfileRequest;
import org.example.userprofile.dto.response.response.UserProfileResponse;
import org.example.userprofile.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping("/userCreate")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUserProfile(@RequestBody UserProfileRequest userProfileRequest) {
        userProfileService.createUserProfile(userProfileRequest);
        return "User profile created!";
    }


    @GetMapping("/getUserProfile")
    @ResponseStatus(HttpStatus.OK)
    public List<UserProfileResponse> getUserProfiles() {
        return userProfileService.getUserProfiles();
    }

    @GetMapping("/getUserProfile/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserProfileResponse getUserProfile(@PathVariable("id") Long userProfileId) {
        return userProfileService.getUserProfile(userProfileId);
    }



}
