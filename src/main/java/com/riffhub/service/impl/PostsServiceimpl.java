package com.riffhub.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.riffhub.Enum.GetPostListParamsEnum;
import com.riffhub.mapper.PostsMapper;
import com.riffhub.mapper.UserMapper;
import com.riffhub.pojo.*;
import com.riffhub.service.NotificationService;
import com.riffhub.service.PostsService;
import com.riffhub.service.TagService;
import com.riffhub.type.GetPostListParams;
import com.riffhub.type.PostDetail;
import com.riffhub.type.PostList;
import com.riffhub.type.ReplyParams;
import com.riffhub.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Max;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PostsServiceimpl implements PostsService {
    @Autowired
    private TagService tagService;
    @Autowired
    private PostsMapper postsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotificationService notificationService;

    @Override
    public void relatedTagsToPost(List<PostTags> list) {
        postsMapper.relatedTagsToPost(list);
    }
    @Override
    public Result publish(Integer userId, Map<String, String> posts) {
        Post post = new Post();

        post.setContent(posts.get("content"));
        post.setTitle(posts.get("title"));
        post.setImgUrl(posts.get("imgUrl"));
        post.setTags(posts.get("tags"));

        User user = userMapper.findByUserId(userId);
        post.setUserId(userId);
        post.setNickname(user.getNickname());
        post.setAvatarUrl(user.getAvatarUrl());

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
    public void reply(User userInfo, ReplyParams replyParams) {

        Integer userId = userInfo.getId();
        Reply reply = new Reply();

        reply.setUserId(userId);
        reply.setNickname(userInfo.getNickname());
        reply.setAvatarUrl(userInfo.getAvatarUrl());
        reply.setPostId(replyParams.getPostId());
        reply.setContent(replyParams.getContent());

        Notification notification = new Notification();

        notification.setType(2);
        notification.setIsDone(false);
        notification.setPostId(replyParams.getPostId());
        notification.setContent(userInfo.getNickname() + " replied you: " + replyParams.getContent());

        Post post = postsMapper.findByPostId(replyParams.getPostId());
        notification.setUserId(post.getUserId());

        if(replyParams.getParentReplyId() != null) {
            reply.setParentReplyId(replyParams.getParentReplyId());

            Reply parentReply = postsMapper.findReplyByReplyId(replyParams.getParentReplyId());
            notification.setUserId(parentReply.getUserId());
        }

        notificationService.add(notification);
        postsMapper.reply(reply);
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
    public List<Post> getPostListByUserId(Integer userId) {
        return postsMapper.findPostByUserId(userId);
    }

    @Override
    public PostList getPostList(Integer userId, GetPostListParams params) {

        GetPostListParamsEnum searchType = params.getSearchType();

        List<PostTags> postTagsList = new ArrayList<>();
        if(searchType == GetPostListParamsEnum.TAG_ID) {
            postTagsList = postsMapper.findPostByTag(params.getTagId());
        }

        PostList postList = new PostList();

        Integer page = params.getPage();
        Integer pageSize = params.getPageSize();
        PageHelper.startPage(page,pageSize);

        List<Post> list = new ArrayList<>();
        switch (searchType) {
            case TITLE:
                list = postsMapper.findPostByTitle(params.getTitle());
                break;
            case USER_ID:
                list = postsMapper.findPostByUserId(params.getUserId());
                break;
            case TAG_ID:
                if(!postTagsList.isEmpty()) {
                    list = postsMapper.findPostByPostId(postTagsList);
                }
                break;
            default:
                list = postsMapper.getPostList();
                break;
        }

        PageInfo<Post> p = new PageInfo<>(list);

        postList.setTotal(p.getTotal());
        postList.setPostList(p.getList());
        for(Post post : postList.getPostList()) {
            Integer postId = post.getId();
            Integer likeCount = postsMapper.getLikeCount(postId);
            Integer replyCount = postsMapper.getReplyCount(postId);
            Like likeUser = postsMapper.findByUseId(userId, postId);
            post.setLikeCount(likeCount);
            post.setReplyCount(replyCount);
            if (likeUser == null) {
                post.setIsLike(false);
            } else {
                post.setIsLike(true);
            }
        }

        return postList;
    }

    @Override
    public Post findByPostId(Integer postId){
        return postsMapper.findByPostId(postId);
    }

    @Override
    public void deletePost(Integer postId) {
        postsMapper.deletePostTag(postId);
        postsMapper.deletePost(postId);
    }

    @Override
    public void likePost(User userInfo, Integer postId, Boolean isLike) {
        Integer userId = userInfo.getId();

        if(isLike) {
            Post post = postsMapper.findByPostId(postId);

            String userName = userInfo.getNickname();
            String content = userName + " liked your post.";
            Notification notification = new Notification();
            notification.setType(1);
            notification.setIsDone(false);
            notification.setUserId(post.getUserId());
            notification.setPostId(postId);
            notification.setContent(content);

            notificationService.add(notification);
            postsMapper.likePost(postId, userId);
        } else {
            postsMapper.dislikePost(postId, userId);
        }

    }

    @Override
    public PostDetail getPostDetail(Integer postId) {
        PostDetail postDetail = new PostDetail();
        Post post = this.findByPostId(postId);
        List<Reply> replyList = postsMapper.getReplyListByPostId(postId);

        postDetail.setPost(post);
        postDetail.setReply(replyList);

       return postDetail;
    }

    @Override
    public List<Post> getPostsByTime(Integer userId,  String timeRange) {
        Calendar calendar = Calendar.getInstance();
        if ("month".equalsIgnoreCase(timeRange)) {
            calendar.add(Calendar.MONTH, -1);
        } else if ("week".equalsIgnoreCase(timeRange)) {
            calendar.add(Calendar.WEEK_OF_YEAR, -1);
        }
        Date startDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(startDate);

        return postsMapper.findPostsByUserIdAndTimeRange(userId, formattedDate);
    }
}
