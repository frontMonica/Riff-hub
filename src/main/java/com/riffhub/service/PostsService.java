package com.riffhub.service;

import com.riffhub.pojo.*;
import com.riffhub.type.GetPostListParams;
import com.riffhub.type.PostDetail;
import com.riffhub.type.PostList;
import com.riffhub.type.ReplyParams;

import java.util.List;
import java.util.Map;

public interface PostsService {
    Result publish(Integer userId, Map<String, String> posts);

    void relatedTagsToPost(List<PostTags> list);
    void reply(User userInfo, ReplyParams replyParams);

    void deleteReply(Integer replyId);

    Reply findByReplyId(Integer replyId);

    PostList getPostList(Integer userId, GetPostListParams params);

    List<Post> getPostListByUserId(Integer userId);

    void deletePost(Integer postID);

    Post findByPostId(Integer postId);

    void likePost(User userInfo, Integer postId, Boolean isLike);

    PostDetail getPostDetail(Integer postId);

}
