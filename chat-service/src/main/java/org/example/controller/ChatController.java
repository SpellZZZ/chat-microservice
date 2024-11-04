package org.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.model.ChatMessage;
import org.example.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.List;


@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/demos")
    public void demo() {
        chatService.demos();
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    @ResponseBody
    public Flux<ChatMessage> getMessages(@PathVariable String senderId, @PathVariable String recipientId) {
        return chatService.getMessages(senderId, recipientId);
    }

    @Autowired
    private ReactiveStringRedisTemplate redisTemplate;

    @GetMapping("/test-redis")
    public Mono<String> testRedis() {
        return redisTemplate.opsForValue().set("testKey", "Hello, Redis!")
                .then(redisTemplate.opsForValue().get("testKey"));
    }

}









/*
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public Mono<ResponseEntity<ChatMessage>> sendMessage(ChatMessage message) {
        return chatService.saveMessage(message.getSenderId(), message.getReceiverId(), message.getMessage())
                .map(savedMessage -> new ResponseEntity<>(savedMessage, HttpStatus.CREATED));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    @ResponseBody
    public Flux<ChatMessage> getMessages(@PathVariable String senderId, @PathVariable String recipientId) {
        return chatService.getMessages(senderId, recipientId);
    }

    @GetMapping("/messages/{recipientId}")
    @ResponseBody
    public Flux<ChatMessage> getMessagesForRecipient(@PathVariable String recipientId) {
        return chatService.getMessagesForRecipient(recipientId);
    }


}
*/