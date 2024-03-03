package com.riffhub.mapper;
import com.riffhub.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void add(String username, String password, String nickname);

    User findByUsername(String username);
}
