package com.esunbank.Charlie.backend.Service;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.esunbank.Charlie.backend.common.domain.dto.LoginDto;
import com.esunbank.Charlie.backend.common.domain.dto.UserDto;
import com.esunbank.Charlie.backend.common.service.UserService;

@SpringBootTest
public class UserServiceTests {
    
    @Autowired
    private UserService userService;
    
    @Test
    public void testRegister() {
        UserDto userDto = new UserDto();
        userDto.setPhoneNumber("0923456789");
        userDto.setPassword("aaa123456");
        userDto.setUserName("Charlie");
        userDto.setRegistrationTime(LocalDateTime.now());
        boolean register = userService.register(userDto);
        System.err.println(register);
        
    }

    @Test
    public void testLoginUser() {
        String phoneNumber = "0923456789";
        String password = "aaa123456";
        LoginDto loginDto = userService.loginUser(phoneNumber, password);
        System.err.println(loginDto.toString());
    }
}
