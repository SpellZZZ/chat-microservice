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


    @GetMapping("/messages/{senderId}/{recipientId}")
    @ResponseBody
    public Flux<ChatMessage> getMessages(@PathVariable String senderId, @PathVariable String recipientId) {
        //return chatService.getMessages(senderId, recipientId);
        return null;
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