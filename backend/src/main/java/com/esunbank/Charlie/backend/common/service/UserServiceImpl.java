package com.esunbank.Charlie.backend.common.service;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esunbank.Charlie.backend.common.converter.UserConverter;
import com.esunbank.Charlie.backend.common.domain.dto.LoginDto;
import com.esunbank.Charlie.backend.common.domain.dto.UserDto;
import com.esunbank.Charlie.backend.common.domain.entity.User;
import com.esunbank.Charlie.backend.common.exception.BadRequestException;
import com.esunbank.Charlie.backend.common.repository.UserRepository;
import com.esunbank.Charlie.backend.common.util.ClientPasswordUtil;
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;


    @Override
    public boolean register(UserDto userDto) {
        if(userRepository.findByPhoneNumber(userDto.getPhoneNumber()) !=0) {
            throw new BadRequestException("Phone number already exists");
        }
        userDto.setPassword(ClientPasswordUtil.encrypt(userDto.getPassword()));
        if (userRepository.InsertUser(userDto.getPhoneNumber(), userDto.getPassword(), userDto.getUserName(), userDto.getRegistrationTime()) > 0) {
            return true;
        }
        return false;
    }
    @Override
    public LoginDto loginUser(String phoneNumber, String password) {

        String encryptedPassword = ClientPasswordUtil.encrypt(password);
        System.out.println(encryptedPassword);
        User existedUser = userRepository.findByPhoneNumberAndPassword(phoneNumber, encryptedPassword);
        if (existedUser!=null){
            existedUser.setLastLoginTime(LocalDateTime.now());
            userRepository.save(existedUser);
            LoginDto loginDto = userConverter.toLoginDto(existedUser);
            return loginDto;
        }
        throw new BadRequestException("Phone number or password is incorrect");
    }

    

}
