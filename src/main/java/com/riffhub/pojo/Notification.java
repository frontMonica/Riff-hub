package com.riffhub.pojo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Notification {
    private Integer id;
    private Integer userId;
    private Integer postId;
    private Boolean isDone;
    private Integer type;
    private String content;
    private LocalDateTime createTime;
}
