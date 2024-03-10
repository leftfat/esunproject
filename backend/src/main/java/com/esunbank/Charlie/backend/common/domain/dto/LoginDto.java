package com.esunbank.Charlie.backend.common.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LoginDto {
    private long userId;
    private String userName;
    private LocalDateTime lastLoginTime;
}
