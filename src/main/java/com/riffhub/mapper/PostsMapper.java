package com.riffhub.mapper;

import com.riffhub.pojo.Post;
import com.riffhub.pojo.PostTags;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostsMapper {
    void add(Post post);
    void relatedTagsToPost(List<PostTags> list);
}
