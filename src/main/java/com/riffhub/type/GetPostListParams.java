package com.riffhub.type;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetPostListParams {
    @Nullable
    private Integer userId;
    @Nullable
    private String title;
    @Nullable
    private Integer tagId;
    @Nullable
    private List<Integer> postIdList = new ArrayList<>();
    private Integer pageSize;
    private Integer page;
}
