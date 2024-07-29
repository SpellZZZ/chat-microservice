package org.example.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import reactor.core.publisher.Mono;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    @Override
    public Mono<Void> handleTextMessage(WebSocketSession session, WebSocketMessage message) {

        String payload = message.getPayloadAsText();
        return session.send(session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .map(session::textMessage));
    }
}