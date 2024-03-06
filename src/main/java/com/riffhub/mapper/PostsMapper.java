package com.riffhub.mapper;

import com.riffhub.pojo.Post;
import com.riffhub.pojo.PostTags;
import com.riffhub.pojo.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface PostsMapper {
    void add(Post post);
    void relatedTagsToPost(List<PostTags> list);
    void reply(Integer postId,Integer userId, String content);
    void deleteReply(Integer replyId);

    Reply findByReplyId(Integer ReplyId);

    List<Post> getPostList(Integer userId, String title, Integer page, Integer pageSize);

    List<Post> getAllPostList(Integer userId, String title);
}
