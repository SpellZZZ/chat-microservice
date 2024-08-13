package org.example.config;


import lombok.RequiredArgsConstructor;
import org.example.model.ChatMessage;
import org.example.repository.ChatMessageRepository;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
@RequiredArgsConstructor
public class CustomWebSocketHandler implements WebSocketHandler {

    private final ReactiveStringRedisTemplate redisTemplate;
    private final Sinks.Many<String> sinks;
    private final ChatMessageRepository messageRepository;
    private final Map<String, Sinks.Many<String>> userSessions = new ConcurrentHashMap<>();


    @Override
    public Mono<Void> handle(WebSocketSession session) {
        String userId = getUserIdFromSession(session.getHandshakeInfo().getUri());
        if (userId == null || userId.isEmpty()) {
            return session.close();
        }

        Sinks.Many<String> sink = userSessions.computeIfAbsent(userId, k -> Sinks.many().multicast().directBestEffort());


        redisTemplate.listenToChannel(userId)
                .map(msg -> msg.getMessage())
                .doOnNext(sink::tryEmitNext)
                .subscribe();

        Mono<Void> input = session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .doOnNext(message -> handleIncomingMessage(message, userId))
                .then();

        Mono<Void> output = session.send(sink.asFlux().map(session::textMessage));

        return Mono.zip(input, output).then()
                .doFinally(signalType -> userSessions.remove(userId));
    }

    private void handleIncomingMessage(String message, String senderId) {
        String[] parts = message.split(":", 2);
        if (parts.length == 2) {
            String receiverId = parts[0];
            String content = parts[1];

            ChatMessage newMessage = ChatMessage.builder()
                    .message(content)
                    .receiverId(receiverId)
                    .senderId(senderId)
                    .timestamp(LocalDateTime.now())
                    .build();
            messageRepository.save(newMessage).subscribe();

            System.out.println("Receiver " + receiverId);
            System.out.println("Sender " + senderId);


            redisTemplate.convertAndSend(receiverId, senderId + ": " + content).subscribe();

            // redisTemplate.convertAndSend(senderId, "You: " + content).subscribe();
        }
    }

    private String getUserIdFromSession(URI uri) {
        String query = uri.getQuery();
        if (query != null) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2 && keyValue[0].equals("userId")) {
                    return keyValue[1];
                }
            }
        }
        return null;
    }
}