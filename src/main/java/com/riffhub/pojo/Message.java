package com.riffhub.pojo;

import jakarta.annotation.Nullable;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Nullable
    private Integer conversationId;
    private Integer senderId;
    private Integer receiverId;
    private String content;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Getters and setters
}
