package com.riffhub.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reply {
    private Integer id;
    private Integer postId;
    private Integer userId;
    private String content;
    private LocalDateTime createTime;
}
