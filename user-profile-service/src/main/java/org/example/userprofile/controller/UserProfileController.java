package org.example.userprofile.controller;


import lombok.RequiredArgsConstructor;
import org.example.userprofile.dto.request.UserRegisterRequest;
import org.example.userprofile.dto.response.UserProfileFullResponse;
import org.example.userprofile.dto.response.UserProfilePartResponse;
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


    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.OK)
    public void createUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        userProfileService.createUserProfile(userRegisterRequest);
    }


    @GetMapping("/getUserProfile")
    @ResponseStatus(HttpStatus.OK)
    public List<UserProfilePartResponse> getUserProfiles() {
        return userProfileService.getUserProfiles();
    }

    @GetMapping("/getUserProfile/{id}")
    public ResponseEntity<UserProfilePartResponse> getUserProfile(@PathVariable("id") Long userProfileId) {

        UserProfilePartResponse userProfilePartResponse = userProfileService.getUserProfile(userProfileId);

        if (userProfilePartResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(userProfilePartResponse);
        }

    }

    @GetMapping("/getUserProfileByName/{userName}")
    public ResponseEntity<UserProfileFullResponse> getUserProfileByUsername(@PathVariable("userName") String userName) {

        UserProfileFullResponse userProfilePartResponse = userProfileService.getUserProfileByUserName(userName);

        if (userProfilePartResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(userProfilePartResponse);
        }
    }

    @GetMapping("/isUserNameExisting/{userName}")
    public boolean isUserNameExisting(@PathVariable("userName") String userName) {
        return userProfileService.isUsernameExisting(userName);
    }
    @GetMapping("/isEmailExisting/{email}")
    public boolean isEmailExisting(@PathVariable("email") String email) {
        return userProfileService.isEmailExisting(email);
    }




}
