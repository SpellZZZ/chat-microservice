package org.example.dto;

import lombok.*;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ChatMessageDto {
    private String senderId;
    private String receiverId;
    private String message;
    private LocalDateTime timestamp;
}
