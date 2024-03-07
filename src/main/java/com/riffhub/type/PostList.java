package com.riffhub.type;

import com.riffhub.pojo.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostList {
    private Long total;
    private List<Post> postList;
}
