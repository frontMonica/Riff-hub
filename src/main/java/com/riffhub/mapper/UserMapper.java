package com.riffhub.mapper;
import com.riffhub.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void add(String username, String password, String nickname);

    User findByUsername(String username);

    User findByUserId(Integer userId);

    List<User> findByBatchId(List<Integer> idList);

    void update(User userInfo);
}
