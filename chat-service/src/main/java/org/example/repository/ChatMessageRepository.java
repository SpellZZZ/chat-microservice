package org.example.repository;

import org.example.model.ChatMessage;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ChatMessageRepository extends ReactiveMongoRepository<ChatMessage, String> {
    Flux<ChatMessage> findBySenderIdAndReceiverId(String senderId, String receiverId);

    Flux<ChatMessage> findByReceiverId(String receiverId);
}