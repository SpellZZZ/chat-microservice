package org.example.service;

import org.example.model.ChatMessage;
import org.example.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public Mono<ChatMessage> saveMessage(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    public Flux<ChatMessage> getMessages(String senderId, String receiverId) {
        return chatMessageRepository.findBySenderIdAndReceiverId(senderId, receiverId);
    }
}