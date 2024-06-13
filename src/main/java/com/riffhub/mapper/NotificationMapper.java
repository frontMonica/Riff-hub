package com.riffhub.mapper;

import com.riffhub.pojo.Notification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationMapper {
    void add(Notification notification);

    List<Notification> getList(Integer userId);

    void update(Integer userId);
}
