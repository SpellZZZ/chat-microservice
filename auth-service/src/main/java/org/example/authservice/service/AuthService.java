package org.example.authservice.service;



import lombok.RequiredArgsConstructor;
import org.example.authservice.dao.request.UserLoginRequest;
import org.example.authservice.dao.request.UserRegisterRequest;
import org.example.authservice.dao.response.TokenResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    public void register(UserRegisterRequest userRegisterRequest) throws Exception {

        /*if(userService.isEmailExisting(userRegisterRequest.getEmail()) ||
                userService.isUsernameExisting(userRegisterRequest.getUsername())) {
            throw new Exception();
        }

        userService.createUserProfile(userRegisterRequest);*/
    }

    public TokenResponse login(UserLoginRequest userLoginRequest) {
        return null;
    }
}
