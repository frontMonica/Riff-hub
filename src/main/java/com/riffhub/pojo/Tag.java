package com.riffhub.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
public class Tag {
    private Integer id;
    private String tagName;
}
