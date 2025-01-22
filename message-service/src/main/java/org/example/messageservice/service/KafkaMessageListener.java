package org.example.messageservice.service;

import lombok.RequiredArgsConstructor;
import org.example.messageservice.dto.ChatMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMessageListener {

    private final ChatService chatService;

    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);


    @KafkaListener(topics = "chat-message", groupId = "msg-group")
    public void consumeEvents(ChatMessageDto chatMessageDto) {
        log.info("consumer consume the events {} ", chatMessageDto.toString());
        chatService.saveMessageInDatabase(chatMessageDto);
    }
}