package com.riffhub.controller;

import com.riffhub.pojo.Result;
import com.riffhub.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
