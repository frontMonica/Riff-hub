package com.riffhub.controller;

import com.riffhub.pojo.Fan;
import com.riffhub.pojo.Result;
import com.riffhub.pojo.User;
import com.riffhub.service.FanService;
import com.riffhub.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fan")
public class FanController {
    @Autowired
    private FanService fanService;

    @PostMapping("/attention")
    public Result attention(Integer attentionId, Boolean isAttention, HttpServletRequest request) {
        Map<String, Object> userInfo = JwtUtil.getLoginUserInfo(request);
        Integer userId = (Integer) userInfo.get("id");
        fanService.attention(attentionId, isAttention, userId);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Fan>> getFansList(Integer userId) {
        return Result.success(fanService.getFansList(userId));
    }

    @GetMapping("/attention/list")
    public Result<List<User>> getAttentionList(Integer attentionId) {
        return Result.success(fanService.getAttentionList(attentionId));
    }

}
