package com.riffhub.service;

import com.riffhub.pojo.Message;

import java.util.List;

public interface MessageService {
    void saveMessage(Message message);

    List<Message> getMessage(Integer user1Id, Integer user2Id);
}
