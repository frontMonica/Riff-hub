package com.riffhub.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private String nickname;
    private Boolean diaryHidden;
    private String avatarUrl;
    private String backgroundUrl;
    private String introduce;
    @Nullable
    private Boolean isAttention;
    private Integer fanCount;
    private Integer postCount;
    private Integer attentionCount;
    private Integer diaryCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
