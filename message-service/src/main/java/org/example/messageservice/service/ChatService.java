package org.example.messageservice.service;

import org.example.messageservice.dto.ChatMessageDto;

public interface ChatService {
    void saveMessageInDatabase(ChatMessageDto chatMessageDto);
}
