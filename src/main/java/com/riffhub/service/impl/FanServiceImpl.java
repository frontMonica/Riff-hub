package com.riffhub.service.impl;

import com.riffhub.mapper.FanMapper;
import com.riffhub.service.FanService;
import com.riffhub.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FanServiceImpl implements FanService {
    @Autowired
    private FanMapper fanMapper;

    @Override
    public void attention(Integer attentionId, Boolean isAttention) {
        Map<String,Object> userInfo = ThreadLocalUtil.get();
        Integer userId = (Integer) userInfo.get("id");
        if(isAttention) {
            fanMapper.attention(attentionId, userId);
        } else {
            fanMapper.cancelAttention(attentionId, userId);
        }
    }
}
