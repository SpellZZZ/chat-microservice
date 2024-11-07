package org.example.messageservice.service;

import org.example.messageservice.dto.ChatMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

    //todo serialization/deserialization
    @KafkaListener(topics = "chat-message", groupId = "msg-group")
    public void consumeEvents(String chatMessageDto) {
        log.info("consumer consume the events {} ", chatMessageDto.toString());
    }
}