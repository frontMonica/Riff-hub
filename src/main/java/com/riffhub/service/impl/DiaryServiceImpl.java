package com.riffhub.service.impl;

import com.riffhub.mapper.DiaryMapper;
import com.riffhub.pojo.Diary;
import com.riffhub.service.DiaryService;
import com.riffhub.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DiaryServiceImpl implements DiaryService {
    @Autowired
    private DiaryMapper diaryMapper;
    @Override
    public void add(Integer userId , String title, String diaryContent, Boolean isHidden) {
        diaryMapper.add(userId, title, diaryContent,isHidden);
    }

    @Override
    public void update(Diary diary) {
        diaryMapper.update(diary);
    }

    @Override
    public List<Diary> getList(Integer userId, Integer loginUserId) {
        if(loginUserId.equals(userId)) {
            return diaryMapper.getAllList(userId);
        } else {
            return diaryMapper.getNotHiddenList(userId);
        }
    }

    @Override
    public void delete(Integer diaryId) {
        diaryMapper.delete(diaryId);
    }
}
