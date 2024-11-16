package com.karmalib.karmalibbackend.common.presentation;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Настройка брокера сообщений
        config.enableSimpleBroker("/topic", "/queue"); // Маршруты для подписчиков
        config.setApplicationDestinationPrefixes("/app"); // Префикс для отправки сообщений
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Регистрация WebSocket-эндоинтов
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }
}