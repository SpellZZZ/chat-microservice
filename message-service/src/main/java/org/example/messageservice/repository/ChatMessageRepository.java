package org.example.messageservice.repository;


import org.example.messageservice.model.ChatMessage;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ChatMessageRepository extends ReactiveMongoRepository<ChatMessage, String> {
    Flux<ChatMessage> findBySenderIdAndReceiverId(String senderId, String receiverId);
    Flux<ChatMessage> findByReceiverId(String receiverId);
}