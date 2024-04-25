package com.riffhub.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reply {
    private Integer id;
    private Integer postId;
    private Integer userId;
    private Integer parentReplyId;
    private String content;
    private String nickname;
    private String avatarUrl;
    private LocalDateTime createTime;
}
