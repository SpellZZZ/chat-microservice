package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.ChatMessage;
import org.example.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final Sinks.Many<String> sinks;

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
        System.out.println("hi");
        return chatMessageRepository.findBySenderIdAndReceiverId(senderId, recipientId);
    }

    public Flux<ChatMessage> getMessagesForRecipient(String recipientId) {
        return chatMessageRepository.findByReceiverId(recipientId);
    }


    public void demos() {
        sinks.emitNext("hello", Sinks.EmitFailureHandler.FAIL_FAST);
    }
}