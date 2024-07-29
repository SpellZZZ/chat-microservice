package org.example.userprofile.service;



import lombok.RequiredArgsConstructor;
import org.example.userprofile.dto.request.UserLoginRequest;
import org.example.userprofile.dto.request.UserRegisterRequest;
import org.example.userprofile.dto.response.TokenResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final WebClient.Builder webClientBuilder;

    public Mono<Void> register(UserRegisterRequest userRegisterRequest) {

        Mono<Boolean> userNameExist = checkUserNameExists(userRegisterRequest.getUsername());
        Mono<Boolean> emailExist = checkEmailExists(userRegisterRequest.getEmail());

        return Mono.zip(emailExist, userNameExist)
                .flatMap(tuple -> {
                    Boolean emailExists = tuple.getT1();
                    Boolean userNameExists = tuple.getT2();

                    if (emailExists || userNameExists) {
                        return Mono.error(new RuntimeException("Username or email already exists"));
                    }

                    return registerUserProfile(userRegisterRequest);
                })
                .doOnSuccess(aVoid -> System.out.println("Registration completed"))
                .doOnError(e -> System.err.println("Error occurred: " + e.getMessage()));
    }


    public TokenResponse login(UserLoginRequest userLoginRequest) {
        return null;
    }


    private Mono<Boolean> checkUserNameExists(String username) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/user-profile/isUserNameExisting/{username}", username)
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    private Mono<Boolean> checkEmailExists(String email) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/user-profile/isEmailExisting/{email}", email)
                .retrieve()
                .bodyToMono(Boolean.class);
    }
    private Mono<Void> registerUserProfile(UserRegisterRequest userRegisterRequest) {
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8081/api/user-profile/createUser")
                .bodyValue(userRegisterRequest)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
