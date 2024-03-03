package com.riffhub.controller;


import com.riffhub.pojo.Result;
import com.riffhub.pojo.User;
import com.riffhub.service.UserService;
import com.riffhub.utils.JwtUtil;
import com.riffhub.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username, String password, String nickname) {
        User user = userService.findByUsername(username);
        if(user == null) {
            userService.register(username,password,nickname);
            return Result.success();
        } else {
            return Result.error("The username is already in use");
        }

    }

    @PostMapping("/login")
    public Result login(String username, String password) {
        User user = userService.findByUsername(username);
        if(user == null) {
            return Result.error("username doesn't exist");
        }
        String md5Password = user.getPassword();

        if(Md5Util.getMD5String(password).equals(md5Password)) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("username",user.getUsername());
            String token = JwtUtil.genToken(claims);
            return  Result.success(token);
        }
        return Result.error("error");
    }
}
