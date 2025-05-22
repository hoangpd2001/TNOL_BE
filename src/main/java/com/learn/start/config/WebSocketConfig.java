package com.learn.start.config;// build.gradle (hoặc pom.xml) cần có dependency spring-boot-starter-websocket

import com.learn.start.config.JWT.JwtUtil;
import com.learn.start.controller.AuthHandshakeInterceptor;
import com.learn.start.controller.SignalingSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final SignalingSocketHandler signalingSocketHandler;

    public WebSocketConfig(SignalingSocketHandler signalingSocketHandler) {
        this.signalingSocketHandler = signalingSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(signalingSocketHandler, "/auth/signal")
                .setAllowedOrigins("*");
    }
}

