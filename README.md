# Chat-microservice

Backend of a chat application built with Spring in a microservice architecture, using WebSockets and Kafka.

## Microservices Overview
* config-service - centralized configuration management using Spring Cloud Config.
* eureka-service - service discovery server using Netflix Eureka.
* message-service - handles sending, receiving, and storing chat messages.
* user-profile-service - manages user data (profiles, login, preferences, etc.).
* webSocket-service - provides real-time communication using WebSocket protocol.


### WebSocket-service

Handles real-time messaging using WebSockets. This service acts as a WebSocket gateway where clients can connect for live chat features.

* Establish WebSocket connections with clients.

* Broadcast messages to chat rooms or individual users.

* Interface with message-service to persist chat history.

* Use Spring WebSocket over WebSocket protocols.

### User-profile-service

Manages user data and authentication/authorization logic.

* User registration and login.

* Role or permission management.

* Exposes REST endpoints for user CRUD operations.


### Message-service

Stores and retrieves chat messages. Acts as a backend for message persistence and retrieval.

* Save incoming chat messages to a database.

* Retrieve chat history for a conversation or group.

* Interface with user-profile-service for user metadata.
