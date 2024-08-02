package org.example.config;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class CustomWebSocketHandler implements WebSocketHandler {

    private final Sinks.Many<String> sinks;


    @Override
    public Mono<Void> handle(WebSocketSession session) {

        //server to client
    /*    var f = sinks.asFlux()
                .map(s -> session.textMessage(s));


        return session.send(f);*/
        var s = session.receive()
                .map(e -> e.getPayloadAsText())
                .map(e -> session.textMessage(new StringBuilder(e).reverse().toString()));

        return session.send(s);

    }
}
