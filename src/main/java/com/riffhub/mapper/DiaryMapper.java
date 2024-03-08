package com.riffhub.mapper;

import com.riffhub.pojo.Diary;

public interface DiaryMapper {
    void add(Integer userId, String title, String diaryContent, Boolean isHidden);

    void update(Diary diary);
}