package org.example.authservice.dao.request;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterRequest {
    private String username;
    private String password;
    private String email;
}
