package com.riffhub.controller;


import com.riffhub.pojo.Fan;
import com.riffhub.pojo.Post;
import com.riffhub.pojo.Result;
import com.riffhub.pojo.User;
import com.riffhub.service.FanService;
import com.riffhub.service.PostsService;
import com.riffhub.service.UserService;
import com.riffhub.type.GetPostListParams;
import com.riffhub.utils.JwtUtil;
import com.riffhub.utils.Md5Util;
import com.riffhub.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FanService fanService;
    @Autowired
    private PostsService postsService;

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
        String md5 = Md5Util.getMD5String(password);
        if(md5.equals(md5Password)) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("username",user.getUsername());
            String token = JwtUtil.genToken(claims);
            User userinfo = ThreadLocalUtil.get();
            System.out.println(userinfo);
            return  Result.success(token);
        }
        return Result.error("incorrect password!");
    }

    @GetMapping("/")
    public Result<User> getUserDetail(Integer userId, HttpServletRequest request) {
        Map<String, Object> userInfo = JwtUtil.getLoginUserInfo(request);
        Integer loginUserId = (Integer) userInfo.get("id");

        Integer realUserId;

        if(userId == null) {
            realUserId = loginUserId;
        } else {
            realUserId = userId;
        }
        List<Fan> fanList = fanService.getFansList(realUserId);
        List<Post> postList = postsService.getPostListByUserId(realUserId);
        List<User> attentionList = fanService.getAttentionList(realUserId);

        User user = userService.findByUserId(realUserId);

        if(userId != null) {
            Boolean isAttenion = fanService.checkAttention(loginUserId, realUserId);
            user.setIsAttention(isAttenion);
        }

        user.setAttentionCount(attentionList.size());
        user.setPostCount(postList.size());
        user.setFanCount(fanList.size());
        return Result.success(user);
    }

    @PostMapping("/update")
    public Result update(User userInfo) {
        userService.update(userInfo);
        return Result.success();
    }

}
