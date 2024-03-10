package com.esunbank.Charlie.backend.common.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esunbank.Charlie.backend.common.domain.entity.BorrowingRecord;
import com.esunbank.Charlie.backend.common.repository.BorrowingRecordRepository;

@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    public Long borrowBook(Long userId, String isbn) {
        LocalDateTime borrowTime = LocalDateTime.now();
        Long result = borrowingRecordRepository.borrowBook(userId, isbn, borrowTime);
        if (result != null && result != 0L) {

            return result;
        }
        return 0L;
    }

    @Override
    public Long returnBook(Long userId, String isbn) {
        LocalDateTime returnTime = LocalDateTime.now();
        Long result = borrowingRecordRepository.returnBook(userId, isbn, returnTime);
        System.err.println();
        if(result!=null&& result!=0L) {
            return result;
        }
        return 0L;
    }
    @Override
    public List<Long> checkIfBookIsBorrowed(Long userId){
        List<BorrowingRecord> queryResult= borrowingRecordRepository.findByUserIdAndReturnTimeIsNull(userId);
        List<Long> result = new ArrayList<Long>();
        for(BorrowingRecord record:queryResult){
            result.add(record.getInventoryId());
        }
        return result;
    }
    @Override
    public List<BorrowingRecord> findBorrowingRecordByUserId(Long userId){
        return borrowingRecordRepository.findByUserIdAndReturnTimeIsNull(userId);
    }
}
