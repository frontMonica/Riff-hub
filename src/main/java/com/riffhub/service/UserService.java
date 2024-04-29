package com.riffhub.service;

import com.riffhub.pojo.User;

public interface UserService {
    void register(String username, String password, String nickname);
    User findByUsername(String username);

    User findByUserId(Integer userId);

    void update(User userInfo);
}
