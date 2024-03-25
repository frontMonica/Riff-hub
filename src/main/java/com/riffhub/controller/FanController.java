package com.riffhub.controller;

import com.riffhub.pojo.Result;
import com.riffhub.service.FanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
