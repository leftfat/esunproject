package com.esunbank.Charlie.backend.common.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.esunbank.Charlie.backend.common.domain.entity.Book;
import com.esunbank.Charlie.backend.common.domain.response.RecordResponse;
@Service
public interface BookService {
    
    public Page<Book> findBooksByBookName(String bookName, int pageNo, int pageSize);

    public List<Book> findAllBooks();

    public List<RecordResponse> checkRecords(Long userId);
}
