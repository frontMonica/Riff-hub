package com.riffhub.pojo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user1Id;
    private Integer user2Id;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
