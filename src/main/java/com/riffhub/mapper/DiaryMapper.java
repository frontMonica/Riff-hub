package com.riffhub.mapper;

import com.riffhub.pojo.Diary;

import java.util.List;

public interface DiaryMapper {
    void add(Integer userId, String title, String diaryContent, Boolean isHidden);

    void update(Diary diary);

    List<Diary> getNotHiddenList(Integer userId);

    List<Diary> getAllList(Integer userId);

    void delete(Integer diaryId);
}