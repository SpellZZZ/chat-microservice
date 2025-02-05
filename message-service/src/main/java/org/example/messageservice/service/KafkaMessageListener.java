package org.example.messageservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.messageservice.dto.ChatMessageDto;
import org.example.messageservice.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class KafkaMessageListener {

    private final ChatService chatService;
    private final ObjectMapper objectMapper;

    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);


    @KafkaListener(topics = "chat-message", groupId = "msg-group")
    public void consumeEvents(String json) throws JsonProcessingException {

        ChatMessageDto chatMessageDto = objectMapper.readValue(json, ChatMessageDto.class);

        log.info("consumer consume the events {} ", chatMessageDto.toString());

        Mono<ChatMessage> chatMessageMono = chatService.saveMessageInDatabase(chatMessageDto);
        chatMessageMono.subscribe(System.out::println);
    }
}