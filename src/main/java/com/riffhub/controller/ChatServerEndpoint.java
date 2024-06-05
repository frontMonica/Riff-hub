package com.riffhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.riffhub.pojo.Message;
import com.riffhub.service.MessageService;
import com.riffhub.utils.JwtUtil;
import com.riffhub.utils.LocalDateTimeSerializer;
import com.riffhub.utils.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/chat")
public class ChatServerEndpoint {

    private static MessageService messageService;
    private static final Map<Session, Integer> userSessions = new ConcurrentHashMap<>();


    @Autowired
    public void setMessageService(MessageService messageService) {
        ChatServerEndpoint.messageService = messageService;
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
    }

    @OnClose
    public void onClose(Session session) {
        userSessions.remove(session);
        System.out.println("Connection closed. Current connections: " + userSessions.size());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        Integer sender = userSessions.get(session);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> msg = objectMapper.readValue(message, Map.class);
            if ("auth".equals(msg.get("type"))) {
                String token = (String) msg.get("token");
                Map<String, Object> info = JwtUtil.parseToken(token);
                Integer userId = (Integer) info.get("id");
                if (userId != null) {
                    userSessions.put(session, userId);
                    System.out.println("Authenticated user: " + userId);
                } else {
                    session.close(new CloseReason(CloseReason.CloseCodes.VIOLATED_POLICY, "Invalid token"));
                }
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Message msg = parseMessage(message);
        System.out.println("发送消息：" + msg);
        if (msg != null && msg.getReceiverId() != null) {
            Integer receiver = msg.getReceiverId();

            userSessions.forEach((s, userId) -> {
                if (userId.equals(receiver)) {
                    try {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
                        Gson gson = gsonBuilder.create();
                        // 将对象转换为 JSON 字符串
                        String json = gson.toJson(msg);
                        s.getBasicRemote().sendText(json);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            messageService.saveMessage(msg);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("Error occurred!");
        error.printStackTrace();
    }

    private Message parseMessage(String jsonMessage) {
        // 解析JSON消息，假设消息格式为 {"receiver": "user_id", "content": "message_content"}
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonMessage, Message.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
