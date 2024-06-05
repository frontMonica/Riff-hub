package com.riffhub.service;

import com.riffhub.pojo.Conversation;
import com.riffhub.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConversationService {
    Conversation findByUser1IdAndUser2Id(Integer user1Id, Integer user2Id);
    void addConversation(Conversation conversation);

    List<User> getChatFriendsDetails(Integer userId);
}
