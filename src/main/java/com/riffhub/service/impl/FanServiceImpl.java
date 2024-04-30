package com.riffhub.service.impl;

import com.riffhub.mapper.FanMapper;
import com.riffhub.mapper.UserMapper;
import com.riffhub.pojo.Fan;
import com.riffhub.pojo.User;
import com.riffhub.service.FanService;
import com.riffhub.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FanServiceImpl implements FanService {
    @Autowired
    private FanMapper fanMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void attention(Integer attentionId, Boolean isAttention, Integer userId) {

        User user = userMapper.findByUserId(userId);
        String avatar = (String) user.getAvatarUrl();
        String nickname = (String) user.getNickname();
        String username = (String) user.getUsername();
        if(isAttention) {
            fanMapper.attention(attentionId, userId, username, nickname, avatar);
        } else {
            fanMapper.cancelAttention(attentionId, userId);
        }
    }

    @Override
    public List<Fan> getFansList(Integer userId) {
        return fanMapper.getFansList(userId);
    }

    @Override
    public List<User> getAttentionList(Integer attentionId) {
        List<Fan> attentionList = fanMapper.getAttentionList(attentionId);
        List<Integer> attentionIdList = new ArrayList<>();

        for (Fan attention : attentionList) {
            attentionIdList.add(attention.getPersonId());
        }
        System.out.println(attentionIdList);
        if(attentionIdList.isEmpty()) {
            return new ArrayList<>();
        }
        return userMapper.findByBatchId(attentionIdList);
    }

    @Override
    public Boolean checkAttention(Integer userId, Integer attentionId) {
        Fan fan = fanMapper.checkAttention(userId, attentionId);
        if (fan == null) {
            return false;
        } else {
            return true;
        }
    }
}
