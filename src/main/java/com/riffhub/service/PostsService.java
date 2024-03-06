package com.riffhub.service;

import com.riffhub.pojo.Post;
import com.riffhub.pojo.PostTags;
import com.riffhub.pojo.Reply;
import com.riffhub.pojo.Result;
import com.riffhub.type.GetPostListParams;
import com.riffhub.type.PostList;

import java.util.List;
import java.util.Map;

public interface PostsService {
    Result publish(Map<String, String> posts);

    void relatedTagsToPost(List<PostTags> list);
    void reply(Integer postId, String content);

    void deleteReply(Integer replyId);

    Reply findByReplyId(Integer replyId);

    PostList getPostList(GetPostListParams params);

    List<Post> getAllPostList(Integer userId, String title,Integer tagId, List<Integer> postIdList);
}
