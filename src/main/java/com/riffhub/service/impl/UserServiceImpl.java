package com.riffhub.service.impl;

import com.riffhub.mapper.UserMapper;
import com.riffhub.pojo.User;
import com.riffhub.service.UserService;
import com.riffhub.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(String username, String password, String nickname) {
        String md5Password = Md5Util.getMD5String(password);
        userMapper.add(username, md5Password, nickname);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
