package com.riffhub.service;

import com.riffhub.pojo.Diary;

import java.util.List;

public interface DiaryService {
    void add(Integer userId , String title, String diaryContent, Boolean isHidden);
    void update(Diary diary);
    List<Diary> getList(Integer userId, Integer loginUserId);

    void delete(Integer diaryId);
}
