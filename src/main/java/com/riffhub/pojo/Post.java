package com.riffhub.pojo;

import jakarta.annotation.Nullable;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class Post {
    private Integer id;
    private Integer userId;
    private String nickname;
    private String title;
    private String content;
    @Nullable
    private Integer likeCount;
    @Nullable
    private Integer replyCount;
    private Boolean isLike;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
