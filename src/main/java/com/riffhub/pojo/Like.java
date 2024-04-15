package com.riffhub.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Like {
    private Integer id;
    private Integer postId;
    private Integer userId;
    private Date likeTime;
}
