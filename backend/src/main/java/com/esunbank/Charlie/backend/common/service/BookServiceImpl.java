package com.esunbank.Charlie.backend.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esunbank.Charlie.backend.common.domain.entity.Book;
import com.esunbank.Charlie.backend.common.domain.entity.BorrowingRecord;
import com.esunbank.Charlie.backend.common.domain.entity.Inventory;
import com.esunbank.Charlie.backend.common.domain.response.RecordResponse;
import com.esunbank.Charlie.backend.common.repository.BookRepository;
import com.esunbank.Charlie.backend.common.repository.BorrowingRecordRepository;
import com.esunbank.Charlie.backend.common.repository.InventoryRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<RecordResponse> checkRecords(Long userId) {

        List<BorrowingRecord> borrowingRecordList = borrowingRecordRepository.findByUserIdAndReturnTimeIsNull(userId);
        List<Long> inventoryIdList = new ArrayList<>();
        borrowingRecordList.forEach(br -> inventoryIdList.add(br.getInventoryId()));

        List<Inventory> inventoryList = inventoryRepository.findByInventoryIdIn(inventoryIdList);
        Map<Long, Inventory> inventoryMap = new HashMap<>();
        List<String> isbnList = new ArrayList<>();
        inventoryList.forEach(i -> {
            inventoryMap.put(i.getInventoryId(), i);
            isbnList.add(i.getIsbn());
        });

        List<Book> bookList = bookRepository.findByIsbnIn(isbnList);
        Map<String, Book> bookMap = new HashMap<>();
        bookList.forEach(b -> bookMap.put(b.getIsbn(), b));

        List<RecordResponse> responseList = new ArrayList<>();

        for (BorrowingRecord borrowingRecord : borrowingRecordList) {

            Inventory inventory = inventoryMap.get(borrowingRecord.getInventoryId());
            if (inventory == null)
                continue;

            Book book = bookMap.get(inventory.getIsbn());
            if (book == null)
                continue;

            responseList.add(new RecordResponse(
                    inventory.getIsbn(),
                    inventory.getInventoryId(),
                    borrowingRecord.getBorrowTime(),
                    book.getBookName()));
        }
        return responseList;

    }

    @Override
    public Page<Book> findBooksByBookName(String bookName, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return bookRepository.findByBookNameContaining(bookName, pageable);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
}
