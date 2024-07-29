package org.example.userprofile.service;



import lombok.RequiredArgsConstructor;
import org.example.userprofile.dto.request.UserJwtRequest;
import org.example.userprofile.dto.request.UserLoginRequest;
import org.example.userprofile.dto.request.UserRegisterRequest;
import org.example.userprofile.dto.response.TokenResponse;
import org.example.userprofile.exception.UserAlreadyExistsException;
import org.example.userprofile.model.UserProfile;
import org.example.userprofile.repository.UserRepo;
import org.example.userprofile.util.JwtUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(UserRegisterRequest userRegisterRequest) {


        boolean emailExist = userRepo.findUserProfileByEmail(userRegisterRequest.getEmail()).isPresent();
        boolean userNameExist = userRepo.findUserProfileByUsername(userRegisterRequest.getUsername()).isPresent();

        if(userNameExist || emailExist) {
            throw new UserAlreadyExistsException();
        }
        UserProfile userProfile = buildUserProfile(userRegisterRequest);

        userRepo.save(userProfile);

    }


    public String login(UserLoginRequest userLoginRequest) {

        Optional<UserProfile> userProfile = userRepo.findUserProfileByUsername(userLoginRequest.getUsername());

        if(userProfile.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }

        UserJwtRequest userJwtRequest = buildUserJwtRequest(userProfile.get());


        return jwtUtil.generateToken(userJwtRequest);
    }

    private UserJwtRequest buildUserJwtRequest(UserProfile userProfile) {
        return UserJwtRequest.builder()
                .username(userProfile.getUsername())
                .password(userProfile.getPassword())
                .build();
    }


    public UserProfile buildUserProfile(UserRegisterRequest userRegisterRequest) {
        return UserProfile.builder()
                .email(userRegisterRequest.getEmail())
                .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                .username(userRegisterRequest.getUsername())
                .build();
    }
}
