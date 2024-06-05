package com.riffhub.controller;

import com.riffhub.pojo.Message;
import com.riffhub.pojo.Result;
import com.riffhub.pojo.User;
import com.riffhub.service.ConversationService;
import com.riffhub.service.MessageService;
import com.riffhub.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private ConversationService conversationService;

    @GetMapping("/list")
    public Result<List<User>> getChatFriends(Integer userId) {
        return Result.success(conversationService.getChatFriendsDetails(userId));
    }

    @GetMapping("/get")
    public Result<List<Message>> getMessage(Integer user1Id, Integer user2Id) {
        return Result.success(messageService.getMessage(user1Id, user2Id));
    }
}
