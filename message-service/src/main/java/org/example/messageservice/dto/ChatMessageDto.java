package org.example.messageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
