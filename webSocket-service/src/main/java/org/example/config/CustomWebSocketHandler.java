package org.example.config;
import org.example.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.example.service.KafkaMessagePublisher;
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

    private final Map<String, Sinks.Many<String>> userSessions = new ConcurrentHashMap<>();
    private final KafkaMessagePublisher publisher;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        String userId = getUserIdFromSession(session.getHandshakeInfo().getUri());

        if (userId == null || userId.isEmpty()) {
            return session.close();
        }

        Sinks.Many<String> sink = registerUser(userId);
        Mono<Void> input = handleInput(session, userId);
        Mono<Void> output = handleOutput(session, sink);

        return Mono.zip(input, output).then()
                .doFinally(signalType -> userSessions.remove(userId));
    }

    private Sinks.Many<String> registerUser(String userId) {
        return userSessions.computeIfAbsent(userId, k -> Sinks.many().multicast().directBestEffort());
    }

    private Mono<Void> handleInput(WebSocketSession session, String userId) {
        return session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .doOnNext(message -> handleIncomingMessage(message, userId))
                .then();
    }

    private Mono<Void> handleOutput(WebSocketSession session, Sinks.Many<String> sink) {
        return session.send(sink.asFlux().map(session::textMessage));
    }

    private void handleIncomingMessage(String message, String senderId) {
        String[] parts = message.split(":", 2);
        if (parts.length == 2) {
            String receiverId = parts[0];
            String content = parts[1];
            ChatMessageDto chatMessageDto = ChatMessageDto.builder()
                    .message(content)
                    .receiverId(receiverId)
                    .senderId(senderId)
                    .timestamp(LocalDateTime.now())
                    .build();

            publisher.sendEventsToTopic(chatMessageDto);
            sendMessageToUser(receiverId, content);
        }
    }

    private void sendMessageToUser(String userId, String message) {
        Sinks.Many<String> sink = userSessions.get(userId);
        if (sink != null) {
            sink.tryEmitNext(message);
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