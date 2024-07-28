package org.example.dto.request;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidRequest {
    private String username;
    private String password;
    private String token;
}
