package com.riffhub.mapper;

import com.riffhub.pojo.Fan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FanMapper {
    void attention(Integer attentionId, Integer userId, String username, String nickname, String avatar);

    void cancelAttention(Integer cancelAttentionId, Integer userId);

    List<Fan> getFansList(Integer userId);

    List<Fan> getAttentionList(Integer attentionId);
}
