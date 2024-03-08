package com.riffhub.service.impl;

import com.riffhub.mapper.DiaryMapper;
import com.riffhub.pojo.Diary;
import com.riffhub.service.DiaryService;
import com.riffhub.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DiaryServiceImpl implements DiaryService {
    @Autowired
    private DiaryMapper diaryMapper;
    @Override
    public void add(String title, String diaryContent, Boolean isHidden) {
        Map<String,Object> userInfo = ThreadLocalUtil.get();
        Integer userId = (Integer) userInfo.get("id");

        diaryMapper.add(userId, title, diaryContent,isHidden);
    }

    @Override
    public void update(Diary diary) {
        diaryMapper.update(diary);
    }
}
