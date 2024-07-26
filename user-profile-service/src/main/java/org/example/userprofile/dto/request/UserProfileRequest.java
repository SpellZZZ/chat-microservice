package org.example.userprofile.dto.request;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileRequest {
    private String username;
    private String password;
    private String email;
}
