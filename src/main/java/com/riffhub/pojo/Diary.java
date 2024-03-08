package com.riffhub.pojo;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class Diary {
    private Integer id;
    private Integer userId;
    @Nullable
    private String title;
    @Nullable
    private String diaryContent;
    @Nullable
    private Boolean isHidden;
}
