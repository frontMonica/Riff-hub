package com.riffhub.pojo;

import lombok.Data;

@Data
public class Fan {
    private Integer id;
    private Integer fanId;
    private Integer personId;
    private String username;
    private String nickname;
    private String avatarUrl;
}
