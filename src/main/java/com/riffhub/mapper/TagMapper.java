package com.riffhub.mapper;

import com.riffhub.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TagMapper {
    Tag findByTagName(String tagName);
    void add(ArrayList<String> tags);
    List<Tag> batchFindByTagName(String [] list);

    Tag getTagById(Integer tagId);

    List<Tag> searchTagByName(String tagName);

}
