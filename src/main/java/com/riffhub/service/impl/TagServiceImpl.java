package com.riffhub.service.impl;

import com.riffhub.mapper.TagMapper;
import com.riffhub.pojo.Tag;
import com.riffhub.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;
    @Override
    public Tag findByTagName(String tagName) {
        return tagMapper.findByTagName(tagName);
    }

    @Override
    public void add(ArrayList<String> tags) {
        tagMapper.add(tags);
    }

    @Override
    public List<Tag> batchFindByTagName(String [] list) {
        return tagMapper.batchFindByTagName(list);
    }

    @Override
    public Tag getTagById(Integer tagId) {
        return tagMapper.getTagById(tagId);
    }

    @Override
    public List<Tag> searchTagByName(String tagName) {
        return tagMapper.searchTagByName(tagName);
    }

}
