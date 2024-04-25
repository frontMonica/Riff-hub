package com.riffhub.type;

import lombok.Data;

@Data
public class ReplyParams {
    private Integer postId;
    private Integer parentReplyId;
    private String content;
}
