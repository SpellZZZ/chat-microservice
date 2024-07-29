package org.example.userprofile.dto.request;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {

    private String username;
    private String password;

}