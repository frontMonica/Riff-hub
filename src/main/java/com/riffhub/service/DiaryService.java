package com.riffhub.service;

import com.riffhub.pojo.Diary;

import java.util.List;

public interface DiaryService {
    void add(String title, String diaryContent, Boolean isHidden);
    void update(Diary diary);
    List<Diary> getList(Integer userId);

    void delete(Integer diaryId);
}
