package org.example.messageservice.service;

import lombok.RequiredArgsConstructor;
import org.example.messageservice.dto.ChatMessageDto;
import org.example.messageservice.model.ChatMessage;
import org.example.messageservice.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatMessageRepository chatMessageRepository;

    public Mono<ChatMessage> saveMessageInDatabase(ChatMessageDto chatMessageDto) {

        ChatMessage chatMessage = ChatMessage.builder()
                .senderId(chatMessageDto.getSenderId())
                .message(chatMessageDto.getMessage())
                .receiverId(chatMessageDto.getReceiverId())
                .timestamp(chatMessageDto.getTimestamp())
                .build();

        System.out.println("saving message: " + chatMessage.toString());
        return chatMessageRepository.save(chatMessage);
    }


}