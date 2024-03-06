package com.riffhub.type;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class GetPostListParams {
    @Nullable
    private Integer userId;
    @Nullable
    private String title;
    private Integer pageSize;
    private Integer page;
}
