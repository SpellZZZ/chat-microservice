package org.example.messageservice.service;

import org.example.messageservice.dto.ChatMessageDto;
import org.example.messageservice.model.ChatMessage;
import reactor.core.publisher.Mono;

public interface ChatService {
    Mono<ChatMessage> saveMessageInDatabase(ChatMessageDto chatMessageDto);
}
