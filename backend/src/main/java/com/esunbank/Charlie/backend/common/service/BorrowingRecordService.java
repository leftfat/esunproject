package com.esunbank.Charlie.backend.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esunbank.Charlie.backend.common.domain.entity.BorrowingRecord;

@Service
public interface BorrowingRecordService {
    
    public Long borrowBook(Long userId, String isbn) ;
    
    public Long returnBook(Long userId, String isbn);

    public List<Long> checkIfBookIsBorrowed(Long userId);

    public List<BorrowingRecord> findBorrowingRecordByUserId(Long userId);
}
