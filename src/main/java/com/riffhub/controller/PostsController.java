package com.riffhub.controller;

import com.riffhub.pojo.Post;
import com.riffhub.pojo.Reply;
import com.riffhub.pojo.Result;
import com.riffhub.service.PostsService;
import com.riffhub.type.GetPostListParams;
import com.riffhub.type.PostList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostsController {
    @Autowired
    private PostsService postsService;

    @PostMapping("/publish")
    public Result publish(@RequestBody Map<String,String> params) {
        return postsService.publish(params);
    }

    @PostMapping("/reply")
    public Result reply(Integer postId, String content) {
        postsService.reply(postId, content);
        return Result.success();
    }

    @DeleteMapping("/reply/delete")
    public Result deleteReply(Integer replyId) {
        Reply reply = postsService.findByReplyId(replyId);
        if (reply == null) {
            return Result.error("Reply does not exist!");
        }
        postsService.deleteReply(replyId);
        return Result.success();
    }
    @PostMapping("/")
    public Result<PostList> getPostList(@RequestBody GetPostListParams params) {
        return Result.success(postsService.getPostList(params));
    }
    @DeleteMapping("/delete")
    public Result deletePost(Integer postId){
        Post post= postsService.findByPostId(postId);
        if (post == null) {
            return Result.error("Post does not exist!");
        }
        postsService.deletePost(postId);
        return Result.success();
    }

}
