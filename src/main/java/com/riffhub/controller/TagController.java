package com.riffhub.controller;

import com.riffhub.pojo.Result;
import com.riffhub.pojo.Tag;
import com.riffhub.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping("/add")
    public Result add(@RequestBody String[] tags) {
        ArrayList<String> newTags = new ArrayList<>();
        for(String tag : tags) {
            Tag isNewTag = tagService.findByTagName(tag);
            if(isNewTag == null) {
                newTags.add(tag);
            }
        }
        if(newTags.isEmpty()) return Result.error("tag has already exist!");
        tagService.add(newTags);
        return Result.success();
    }

    @GetMapping("/get")
    public Result<Tag> getTagById(Integer tagId) {
        return Result.success(tagService.getTagById(tagId));
    }

    @GetMapping("/get/name")
    public Result<List<Tag>> searchTagByName(String tagName) {
        return Result.success(tagService.searchTagByName(tagName));
    }
}
