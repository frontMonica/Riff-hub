package com.riffhub.type;

import com.riffhub.pojo.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostList {
    private Integer total;
    private List<Post> postList;
}
