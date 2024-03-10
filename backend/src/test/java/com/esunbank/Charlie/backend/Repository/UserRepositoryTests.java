package com.esunbank.Charlie.backend.Repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.esunbank.Charlie.backend.common.repository.UserRepository;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Integer result = userRepository.InsertUser("0923456789", "aaa123456", "test123", localDateTime);
        System.err.println(result);
    }

    @Test
    public void testFindByPhoneNumber() {
        Integer result = userRepository.findByPhoneNumber("0923456789");
        System.err.println(result);
    }

    // @Test
    // public void testLoginUser() {
    //     LoginDto loginUser = userRepository.LoginUser("0923456789", "aaa123456");
    //     System.err.println(loginUser.toString());
    // }
    
}
