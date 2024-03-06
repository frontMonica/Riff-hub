package com.riffhub.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.riffhub.mapper.PostsMapper;
import com.riffhub.pojo.*;
import com.riffhub.service.PostsService;
import com.riffhub.service.TagService;
import com.riffhub.type.GetPostListParams;
import com.riffhub.type.PostList;
import com.riffhub.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Max;
import org.apache.ibatis.session.RowBounds;
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

    @Override
    public List<Post> getAllPostList(Integer userId, String title,Integer tagId, List<Integer> postIdList) {
        return postsMapper.getAllPostList(userId,title,tagId,postIdList);
    }

    @Override
    public PostList getPostList(GetPostListParams params) {

        PostList postList = new PostList();

        Integer page = params.getPage();
        Integer pageSize = params.getPageSize();

        Integer tagId = params.getTagId();

        if(tagId != null) {
            List<PostTags> postTagsList = postsMapper.findPostByTag(tagId);
            List<Integer> idList = new ArrayList<>();

            for(PostTags postTag : postTagsList) {
                idList.add(postTag.getPostId());
            }
            params.setPostIdList(idList);
        }

        List<Post> list = postsMapper.getPostList(params.getUserId(), params.getTitle(),params.getTagId(),params.getPostIdList(),page,pageSize);

        List<Post> allList = postsMapper.getAllPostList(params.getUserId(), params.getTitle(),params.getTagId(),params.getPostIdList());

        postList.setTotal(allList.size());
        postList.setPostList(list);

        return postList;
    }

}
