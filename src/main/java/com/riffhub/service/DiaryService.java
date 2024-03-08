package com.riffhub.service;

import com.riffhub.pojo.Diary;

public interface DiaryService {
    void add(String title, String diaryContent, Boolean isHidden);
    void update(Diary diary);
}
