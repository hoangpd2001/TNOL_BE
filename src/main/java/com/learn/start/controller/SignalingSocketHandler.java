package com.learn.start.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.start.config.JWT.JwtUtil;
import com.learn.start.security.CustomUserDetails;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
@Component
public class SignalingSocketHandler extends TextWebSocketHandler {
    public List<String> getActiveRoomIds() {
        return new ArrayList<>(rooms.keySet());
    }

    private final JwtUtil jwtUtil;

    public SignalingSocketHandler(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // Dùng String làm roomId vì ta dùng "teacher-123"
    private final Map<String, List<WebSocketSession>> rooms = new ConcurrentHashMap<>();
    private final Map<String, String> sessionToRoom = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String query = session.getUri().getQuery();
        String token = null;

        if (query != null) {
            for (String param : query.split("&")) {
                String[] pair = param.split("=");
                if (pair.length == 2 && pair[0].equals("token")) {
                    token = pair[1];
                    break;
                }
            }
        }

        if (token == null || !jwtUtil.validateToken(token)) {
            session.close(CloseStatus.BAD_DATA);
            return;
        }

        CustomUserDetails userDetails = jwtUtil.extractToken(token);
        session.getAttributes().put("user", userDetails);
        System.out.println("Connected: " + userDetails.getGmail());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        JSONObject jsonMessage = new JSONObject(message.getPayload());
        String type = jsonMessage.getString("type");

        CustomUserDetails user = (CustomUserDetails) session.getAttributes().get("user");
        if (user == null) {
            session.sendMessage(new TextMessage("{\"error\": \"Unauthorized\"}"));
            return;
        }

        switch (type) {
            case "join":
                String role = jsonMessage.getString("role");
                if (jsonMessage.has("roomId")) {
                    session.getAttributes().put("requestedRoom", jsonMessage.getString("roomId"));
                }

                handleJoin(session, role, user);
                break;
            case "offer":
            case "answer":
            case "ice-candidate":
                forwardMessage(session, jsonMessage);
                break;
            default:
                session.sendMessage(new TextMessage("{\"error\": \"Unknown message type\"}"));
        }
    }

    private void handleJoin(WebSocketSession session, String role, CustomUserDetails user) throws IOException {
        String roomId;
        if ("broadcaster".equals(role)) {
            roomId = "teacher-" + user.getId();
        } else {
            roomId = (String) session.getAttributes().get("requestedRoom");
            if (roomId == null) return;
        }

        session.getAttributes().put("roomId", roomId);
        session.getAttributes().put("role", role);

        rooms.computeIfAbsent(roomId, k -> new CopyOnWriteArrayList<>()).add(session);
        sessionToRoom.put(session.getId(), roomId);

        // 🚀 Gửi thông báo cho tất cả người khác trong room
        JSONObject notify = new JSONObject();
        notify.put("type", "viewer-joined");
        notify.put("viewerId", user.getId());
        notify.put("viewerGmail", user.getGmail());

        for (WebSocketSession s : rooms.get(roomId)) {
            if (!s.getId().equals(session.getId()) && s.isOpen()) {
                s.sendMessage(new TextMessage(notify.toString()));
            }
        }
    }


    private void forwardMessage(WebSocketSession sender, JSONObject message) throws IOException {
        String roomId = (String) sender.getAttributes().get("roomId");
        if (roomId == null || !rooms.containsKey(roomId)) return;

        CustomUserDetails senderUser = (CustomUserDetails) sender.getAttributes().get("user");
        if (senderUser == null) return;

        String senderRole = (String) sender.getAttributes().get("role");
        String fromId = senderRole + "-" + senderUser.getId(); // ví dụ: "teacher-100065" hoặc "viewer-100063"
        message.put("from", fromId);

        for (WebSocketSession session : rooms.get(roomId)) {
            if (!session.getId().equals(sender.getId()) && session.isOpen()) {
                session.sendMessage(new TextMessage(message.toString()));
            }
        }
    }




    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String roomId = sessionToRoom.remove(session.getId());
        if (roomId != null) {
            List<WebSocketSession> roomSessions = rooms.get(roomId);
            if (roomSessions != null) {
                roomSessions.remove(session);
                if (roomSessions.isEmpty()) {
                    rooms.remove(roomId);
                }
            }
        }
    }
}

