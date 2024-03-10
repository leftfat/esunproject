package com.esunbank.Charlie.backend.common.service;

import org.springframework.stereotype.Service;

import com.esunbank.Charlie.backend.common.domain.dto.LoginDto;
import com.esunbank.Charlie.backend.common.domain.dto.UserDto;

@Service
public interface UserService {

    public boolean register(UserDto userDto);
    public LoginDto loginUser(String phoneNumber, String password);
}
