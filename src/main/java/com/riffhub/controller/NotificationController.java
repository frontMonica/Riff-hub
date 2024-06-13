package com.riffhub.controller;

import com.riffhub.pojo.Notification;
import com.riffhub.pojo.Result;
import com.riffhub.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/get")
    public Result<List<Notification>> getList(Integer userId) {
        return Result.success(notificationService.getList(userId));
    }

    @PostMapping("/update")
    public Result update(Integer userId) {
        notificationService.update(userId);
        return Result.success();
    }

}
