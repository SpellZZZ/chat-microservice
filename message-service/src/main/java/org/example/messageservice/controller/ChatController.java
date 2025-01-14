package org.example.messageservice.controller;


import lombok.RequiredArgsConstructor;
import org.example.messageservice.model.ChatMessage;
import org.example.messageservice.service.ChatService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    //todo
    @GetMapping("/messages/{senderId}/{recipientId}")
    @ResponseBody
    public Flux<ChatMessage> getMessages(@PathVariable String senderId, @PathVariable String recipientId) {
        return null;
    }
}

