package com.riffhub.service.impl;

import com.riffhub.mapper.NotificationMapper;
import com.riffhub.pojo.Notification;
import com.riffhub.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public void add(Notification notification) {
        notificationMapper.add(notification);
    }

    @Override
    public List<Notification> getList(Integer userId) {
        return notificationMapper.getList(userId);
    }

    @Override
    public void update(Integer userId) {
        notificationMapper.update(userId);
    }
}
