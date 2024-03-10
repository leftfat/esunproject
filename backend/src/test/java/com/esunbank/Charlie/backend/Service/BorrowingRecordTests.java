package com.esunbank.Charlie.backend.Service;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.esunbank.Charlie.backend.common.service.BorrowingRecordService;

@SpringBootTest
public class BorrowingRecordTests {
    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @Test
    public void testBorrowBook() {
        Long result = borrowingRecordService.borrowBook
        (1L, "9780131103627");
        System.err.println(result);
    }
    @Test
    public void testCheckIfBookIsBorrowed() {
        List<Long> result = borrowingRecordService.checkIfBookIsBorrowed(2L);
        for (Long inventoryId : result) {
            System.err.println(inventoryId);
        }
    }
}
