package com.esunbank.Charlie.backend.common.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esunbank.Charlie.backend.common.converter.UserConverter;
import com.esunbank.Charlie.backend.common.domain.dto.UserDto;
import com.esunbank.Charlie.backend.common.domain.request.BorrowRequest;
import com.esunbank.Charlie.backend.common.domain.request.LoginRequest;
import com.esunbank.Charlie.backend.common.domain.request.ReturnRequest;
import com.esunbank.Charlie.backend.common.domain.request.UserRequest;
import com.esunbank.Charlie.backend.common.service.BookService;
import com.esunbank.Charlie.backend.common.service.BorrowingRecordService;
import com.esunbank.Charlie.backend.common.service.UserService;

import jakarta.validation.Valid;



@RestController
//避免cross協議導致無法前後端無法連線
@CrossOrigin(origins = "*")
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;
    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @Autowired
    private BookService bookService;


    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRequest request) {
        UserDto userDto = userConverter.toUserDto(request);
        userDto.setRegistrationTime(LocalDateTime.now());
        boolean register = userService.register(userDto);
        if(register){
        return ResponseEntity.created(null).body(null);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity
        .ok(userService.loginUser(request.getPhoneNumber(), request.getPassword()));
    }

    @PostMapping(path = "/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowRequest request) {
        Long inventoryId = borrowingRecordService.borrowBook(request.getUserId(), request.getIsbn());
        System.err.println("inventoryId = "+inventoryId);
        return ResponseEntity.ok(inventoryId);
    }
    
    @PostMapping(path = "/return")
    public ResponseEntity<?> returnBook(@RequestBody ReturnRequest request) {
        Long entity = borrowingRecordService.returnBook(request.getUserId(), request.getIsbn());
        if(entity == 0L) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(entity);
    }
    
    @GetMapping(path = "/checkBooks")
    public List<Long> checkIfBookIsBorrowed(@RequestParam Long userId) {
        return borrowingRecordService.checkIfBookIsBorrowed(userId);
    }
    
    @GetMapping(path = "/borrowingRecord")
    public ResponseEntity<?> findBorrowingRecordByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(bookService.checkRecords(userId));
}
}

