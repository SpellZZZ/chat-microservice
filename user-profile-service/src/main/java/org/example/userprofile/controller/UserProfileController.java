package org.example.userprofile.controller;


import lombok.RequiredArgsConstructor;
import org.example.userprofile.service.UserProfileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;



}
