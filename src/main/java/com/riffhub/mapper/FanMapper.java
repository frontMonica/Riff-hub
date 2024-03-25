package com.riffhub.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FanMapper {
    void attention(Integer attentionId, Integer userId);

    void cancelAttention(Integer cancelAttentionId, Integer userId);
}
