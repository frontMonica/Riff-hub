package com.riffhub.service;

import com.riffhub.pojo.PostTags;
import com.riffhub.pojo.Result;

import java.util.List;
import java.util.Map;

public interface PostsService {
    Result publish(Map<String, String> posts);

    void relatedTagsToPost(List<PostTags> list);
}
