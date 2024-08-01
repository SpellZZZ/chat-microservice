package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.ChatMessage;
import org.example.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    public Mono<ChatMessage> saveMessage(String senderId, String recipientId, String content) {
        ChatMessage message = ChatMessage.builder()
                .senderId(senderId)
                .receiverId(recipientId)
                .message(content)
                .timestamp(LocalDateTime.now())
                .build();

        return chatMessageRepository.save(message);
    }

    public Flux<ChatMessage> getMessages(String senderId, String recipientId) {
        return chatMessageRepository.findBySenderIdAndReceiverId(senderId, recipientId);
    }

    public Flux<ChatMessage> getMessagesForRecipient(String recipientId) {
        return chatMessageRepository.findByReceiverId(recipientId);
    }












}