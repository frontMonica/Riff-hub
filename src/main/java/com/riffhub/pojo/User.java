package com.riffhub.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

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
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
