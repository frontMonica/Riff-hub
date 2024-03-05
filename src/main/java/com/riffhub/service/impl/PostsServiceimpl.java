package com.riffhub.service.impl;

import com.riffhub.mapper.PostsMapper;
import com.riffhub.pojo.*;
import com.riffhub.service.PostsService;
import com.riffhub.service.TagService;
import com.riffhub.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.*;

@Service
public class PostsServiceimpl implements PostsService {
    @Autowired
    private TagService tagService;
    @Autowired
    private PostsMapper postsMapper;

    @Override
    public void relatedTagsToPost(List<PostTags> list) {
        postsMapper.relatedTagsToPost(list);
    }
    @Override
    public Result publish(Map<String, String> posts) {
        Post post = new Post();

        post.setContent(posts.get("content"));
        post.setTitle(posts.get("title"));
        Map<String,Object> userInfo = ThreadLocalUtil.get();
        Integer id = (Integer) userInfo.get("id");
        post.setUserId(id);
        post.setNickname(posts.get("nickname"));

        System.out.println(posts);

        String[] list = posts.get("tags").split(",");
        ArrayList<String> newTags = new ArrayList<>();
        for(String tag : list) {
            Tag isNewTag = tagService.findByTagName(tag);
            if(isNewTag == null) newTags.add(tag);
        }
        if(!newTags.isEmpty()) tagService.add(newTags);

        postsMapper.add(post);

        Integer postId = post.getId();
        List<Tag> tagList = tagService.batchFindByTagName(list);

        List<PostTags> postTagsList = new ArrayList<>();

        for(Tag tag : tagList) {
            PostTags postTags = new PostTags();

            postTags.setPostId(postId);
            postTags.setTagId(tag.getId());

            postTagsList.add(postTags);
        }

        this.relatedTagsToPost(postTagsList);

        return Result.success();
    }

    @Override
    public void reply(Integer postId, String content) {
        Map<String,Object> userInfo = ThreadLocalUtil.get();
        Integer userId = (Integer) userInfo.get("id");
        postsMapper.reply(postId, userId, content);
    }


    @Override
    public Reply findByReplyId(Integer replyID){
       return postsMapper.findByReplyId(replyID);
    }

    @Override
    public void deleteReply(Integer replyId) {
        postsMapper.deleteReply(replyId);
    }


}
