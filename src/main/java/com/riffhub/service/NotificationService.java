package com.riffhub.service;

import com.riffhub.pojo.Notification;

import java.util.List;

public interface NotificationService {
    void add(Notification notification);

    List<Notification> getList(Integer userId);

    void update(Integer userId);
}
