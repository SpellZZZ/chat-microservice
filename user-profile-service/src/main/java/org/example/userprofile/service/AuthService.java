package org.example.userprofile.service;



import lombok.RequiredArgsConstructor;
import org.example.userprofile.dto.request.UserLoginRequest;
import org.example.userprofile.dto.request.UserRegisterRequest;
import org.example.userprofile.dto.response.TokenResponse;
import org.example.userprofile.exception.UserAlreadyExistsException;
import org.example.userprofile.model.UserProfile;
import org.example.userprofile.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public void register(UserRegisterRequest userRegisterRequest) {


        boolean emailExist = userRepo.findUserProfileByEmail(userRegisterRequest.getEmail()).isPresent();
        boolean userNameExist = userRepo.findUserProfileByUsername(userRegisterRequest.getUsername()).isPresent();

        if(userNameExist || emailExist) {
            throw new UserAlreadyExistsException();
        }
        UserProfile userProfile = buildUserProfile(userRegisterRequest);

        userRepo.save(userProfile);

    }


    public TokenResponse login(UserLoginRequest userLoginRequest) {
        return null;
    }



    public UserProfile buildUserProfile(UserRegisterRequest userRegisterRequest) {
        return UserProfile.builder()
                .email(userRegisterRequest.getEmail())
                .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                .username(userRegisterRequest.getUsername())
                .build();
    }
}
