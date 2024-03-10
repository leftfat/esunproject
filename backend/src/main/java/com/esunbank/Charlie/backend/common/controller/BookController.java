package com.esunbank.Charlie.backend.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esunbank.Charlie.backend.common.domain.entity.Book;
import com.esunbank.Charlie.backend.common.service.BookService;

@RestController
// 避免cross協議導致無法前後端無法連線
@CrossOrigin(origins = "*")
@RequestMapping(path = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<?> findBooksByBookName(
            @RequestParam(value = "bookName", required = false, defaultValue = "") String bookName,
            @RequestParam(value = "page", defaultValue = "0" ) int pageNo,
            @RequestParam(value = "size", defaultValue = "3") int pageSize) {

        Page<Book> page = bookService.findBooksByBookName(bookName, pageNo, pageSize);
        return ResponseEntity.ok(page);
    }
}
