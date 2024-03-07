package com.riffhub.type;

import com.riffhub.Enum.GetPostListParamsEnum;
import com.riffhub.pojo.PostTags;
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
    private List<PostTags> postIdList = new ArrayList<>();

    private GetPostListParamsEnum searchType;
    private Integer pageSize;
    private Integer page;
}
