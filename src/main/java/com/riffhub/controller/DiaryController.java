package com.riffhub.controller;

import com.riffhub.pojo.Diary;
import com.riffhub.pojo.Result;
import com.riffhub.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diary")
public class DiaryController {
    @Autowired
    private DiaryService diaryService;

    @PostMapping("/add")
    public Result add(String title, String diaryContent, Boolean isHidden) {
        diaryService.add(title, diaryContent,isHidden);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Diary diary) {
        diaryService.update(diary);
        return Result.success();
    }
}
