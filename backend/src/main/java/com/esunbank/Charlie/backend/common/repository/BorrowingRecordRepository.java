package com.esunbank.Charlie.backend.common.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.esunbank.Charlie.backend.common.domain.entity.BorrowingRecord;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    @Procedure(name = "BorrowBook")
    Long borrowBook(Long userId, String isbn, LocalDateTime borrowTime);

    @Procedure(name = "ReturnBook")
    Long returnBook(Long userId, String isbn, LocalDateTime returnTime);

    List<BorrowingRecord> findByUserIdAndReturnTimeIsNull(Long userId);

    List<BorrowingRecord> findByUserIdAndReturnTimeIsNullAndBorrowTimeIsNotNull(Long userId);

    List<BorrowingRecord> findByUserId(Long userId);

}
