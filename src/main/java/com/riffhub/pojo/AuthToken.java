package com.riffhub.pojo;

import lombok.Data;

@Data
public class AuthToken {
    private String type;
    private String token;
}
