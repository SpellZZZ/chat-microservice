package org.example.messageservice.service;

import lombok.RequiredArgsConstructor;
import org.example.messageservice.dto.ChatMessageDto;
import org.example.messageservice.model.ChatMessage;
import org.example.messageservice.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatMessageRepository chatMessageRepository;

    public void saveMessageInDatabase(ChatMessageDto chatMessageDto) {

        ChatMessage chatMessage = ChatMessage.builder()
                .senderId(chatMessageDto.getSenderId())
                .message(chatMessageDto.getMessage())
                .receiverId(chatMessageDto.getReceiverId())
                .timestamp(chatMessageDto.getTimestamp())
                .build();

        chatMessageRepository.save(chatMessage);
    }


}