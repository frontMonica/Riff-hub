package com.riffhub.service.impl;

import com.riffhub.mapper.ConversationMapper;
import com.riffhub.pojo.Conversation;
import com.riffhub.pojo.User;
import com.riffhub.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {
    @Autowired
    private ConversationMapper conversationMapper;

    @Override
    public Conversation findByUser1IdAndUser2Id(Integer user1Id, Integer user2Id){
        return conversationMapper.findByUser1IdAndUser2Id(user1Id, user2Id);
    }

    @Override
    public void addConversation(Conversation conversation) {
        conversationMapper.addConversation(conversation);
    }

    @Override
    public List<User> getChatFriendsDetails(Integer userId) {
        return conversationMapper.getChatFriendsDetails(userId);
    }
}
