package com.riffhub.mapper;

import com.riffhub.pojo.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    void addMessage(Message message);

    List<Message> getConversationMessages(Integer conversationId);
}
