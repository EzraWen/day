package com.ezra.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RedisObject {


    private Long id;

    private String name;

    private LocalDateTime ctime;

    private LocalDateTime utime;

    private Boolean status;
}
