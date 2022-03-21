package com.hyunjung.finalproject.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskCreatReq {
    private final String title;
    private final String description;
    private final LocalDateTime taskAt;
}
