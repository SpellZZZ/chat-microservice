package org.example.config.webSocket;

import org.example.config.redis.Publisher;
import org.example.config.redis.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import reactor.core.publisher.Sinks;

import java.util.Map;

@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    WebSocketSessionManager webSocketSessionManager;

    @Autowired
    Publisher redisPublisher;

    @Autowired
    Subscriber redisSubscriber;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler()
    }

   /* @Bean
    public SimpleUrlHandlerMapping handlerMapping(WebSocketHandler webSocketHandler){
        return new SimpleUrlHandlerMapping(Map.of("/ws/messages", webSocketHandler),1);
    }*/

    @Bean
    public WebSocketHandlerAdapter webSocketHandlerAdapter(){
        return new WebSocketHandlerAdapter();
    }

    @Bean
    public Sinks.Many<String> sink(){
        return Sinks.many().multicast().directBestEffort();
    }


}