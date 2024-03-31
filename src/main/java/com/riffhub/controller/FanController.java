package com.riffhub.controller;

import com.riffhub.pojo.Fan;
import com.riffhub.pojo.Result;
import com.riffhub.pojo.User;
import com.riffhub.service.FanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fan")
public class FanController {
    @Autowired
    private FanService fanService;

    @PostMapping("/attention")
    public Result attention(Integer attentionId, Boolean isAttention) {
        fanService.attention(attentionId, isAttention);
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
