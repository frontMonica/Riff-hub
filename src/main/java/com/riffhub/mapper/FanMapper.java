package com.riffhub.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FanMapper {
    void attention(Integer attentionId, Integer userId, String username, String nickname, String avatar);

    void cancelAttention(Integer cancelAttentionId, Integer userId);
}
