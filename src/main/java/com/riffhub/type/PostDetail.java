package com.riffhub.type;

import com.riffhub.pojo.Post;
import com.riffhub.pojo.Reply;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDetail {
    private Post post;
    private List<Reply> reply;
}
