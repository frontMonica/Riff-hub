package com.riffhub.service.impl;

import com.riffhub.mapper.MessageMapper;
import com.riffhub.pojo.Conversation;
import com.riffhub.pojo.Message;
import com.riffhub.service.ConversationService;
import com.riffhub.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private ConversationService conversationService;

    @Override
    public void saveMessage(Message message) {
        Conversation conversation = conversationService
                .findByUser1IdAndUser2Id(message.getSenderId(), message.getReceiverId());

        if (conversation == null) {
            // 如果会话不存在，创建一个新的会话
            Conversation newConversation = new Conversation();
            newConversation.setUser1Id(message.getSenderId());
            newConversation.setUser2Id(message.getReceiverId());
            conversationService.addConversation(newConversation);
            message.setConversationId(newConversation.getId());
        } else {
            message.setConversationId(conversation.getId());
        }

        // 保存消息
        messageMapper.addMessage(message);
    }

    @Override
    public List<Message> getMessage(Integer user1Id, Integer user2Id) {
        Conversation conversation = conversationService.findByUser1IdAndUser2Id(user1Id, user2Id);
        Integer conversationId = conversation.getId();

        List<Message> messageList = messageMapper.getConversationMessages(conversationId);

        return  messageList;
    }
}
