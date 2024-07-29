package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.WebSocketHandlerMapping;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebFlux
public class WebSocketConfig implements WebFluxConfigurer {

    @Bean
    public WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

    @Bean
    public WebSocketHandlerMapping webSocketHandlerMapping(ChatWebSocketHandler chatWebSocketHandler) {
        Map<String, WebSocketHandler> map = new HashMap<>();
        map.put("/ws/chat", chatWebSocketHandler);

        WebSocketHandlerMapping handlerMapping = new WebSocketHandlerMapping();
        handlerMapping.setOrder(-1);
        handlerMapping.setUrlMap(map);
        return handlerMapping;
    }
}