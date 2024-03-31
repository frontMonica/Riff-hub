package com.riffhub.service;

import com.riffhub.pojo.Fan;
import com.riffhub.pojo.User;

import java.util.List;

public interface FanService {
    void attention(Integer attentionId, Boolean isAttention);

    List<Fan> getFansList(Integer userId);

    List<User> getAttentionList(Integer attentionId);
}
