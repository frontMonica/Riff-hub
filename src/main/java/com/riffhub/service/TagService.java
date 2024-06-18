package com.riffhub.service;

import com.riffhub.pojo.Result;
import com.riffhub.pojo.Tag;

import java.util.ArrayList;
import java.util.List;

public interface TagService {
    Tag findByTagName(String tagName);
    void add(ArrayList<String> tags);

    List<Tag> batchFindByTagName(String [] list);

    List<Tag> getTags();

    List<Tag> searchTagByName(String tagName);
}
