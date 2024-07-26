package org.example.userprofile.controller;


import lombok.RequiredArgsConstructor;
import org.example.userprofile.dto.response.UserProfileResponse;
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

    @GetMapping("/getUserProfile")
    @ResponseStatus(HttpStatus.OK)
    public List<UserProfileResponse> getUserProfiles() {
        return userProfileService.getUserProfiles();
    }

    @GetMapping("/getUserProfile/{id}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable("id") Long userProfileId) {

        UserProfileResponse userProfileResponse = userProfileService.getUserProfile(userProfileId);

        if (userProfileResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(userProfileResponse);
        }

    }

}
