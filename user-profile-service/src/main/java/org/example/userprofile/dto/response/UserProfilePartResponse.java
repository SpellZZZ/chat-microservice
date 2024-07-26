package org.example.userprofile.dto.response;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfilePartResponse {
    private Long id;
    private String username;
    private String email;
}
