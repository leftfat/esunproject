package com.esunbank.Charlie.backend.common.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDto {
    private String phoneNumber;
    private String password;
    private String userName;
    private LocalDateTime registrationTime;
}
