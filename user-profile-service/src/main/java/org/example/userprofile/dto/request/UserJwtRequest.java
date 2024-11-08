package org.example.userprofile.dto.request;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserJwtRequest {
    private String username;
    private String password;
}