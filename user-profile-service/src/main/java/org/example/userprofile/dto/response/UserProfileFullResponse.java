package org.example.userprofile.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileFullResponse {
    private Long id;
    private String username;
    private String password;
    private String email;
}
