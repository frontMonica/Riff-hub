package com.riffhub.controller;

import com.riffhub.pojo.Diary;
import com.riffhub.pojo.Result;
import com.riffhub.service.DiaryService;
import com.riffhub.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/diary")
public class DiaryController {
    @Autowired
    private DiaryService diaryService;

    @PostMapping("/add")
    public Result add(String title, String diaryContent, Boolean isHidden,  HttpServletRequest request) {
        Map<String, Object> userInfo = JwtUtil.getLoginUserInfo(request);
        Integer userId = (Integer) userInfo.get("id");
        diaryService.add(userId ,title, diaryContent,isHidden);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Diary diary) {
        diaryService.update(diary);
        return Result.success();
    }

    @GetMapping("/")
    public Result<List<Diary>> getList(Integer userId,  HttpServletRequest request) {
        Map<String, Object> userInfo = JwtUtil.getLoginUserInfo(request);
        Integer loginUserId = (Integer) userInfo.get("id");
        return Result.success(diaryService.getList(userId, loginUserId));
    }

    @PostMapping("/delete")
    public Result delete(Integer diaryId) {
        diaryService.delete(diaryId);
        return Result.success();
    }
}
