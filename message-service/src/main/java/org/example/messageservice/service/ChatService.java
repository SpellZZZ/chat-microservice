package org.example.messageservice.service;

import lombok.RequiredArgsConstructor;
import org.example.messageservice.model.ChatMessage;
import org.example.messageservice.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatService {

}