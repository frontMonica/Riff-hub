package com.riffhub.mapper;

import com.riffhub.pojo.Conversation;
import com.riffhub.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConversationMapper {
    Conversation findByUser1IdAndUser2Id(Integer user1Id, Integer user2Id);

    void addConversation(Conversation conversation);

    List<User> getChatFriendsDetails(Integer userId);
}
