package com.esunbank.Charlie.backend.common.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esunbank.Charlie.backend.common.domain.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    @Query("SELECT b FROM Book b WHERE b.bookName LIKE %:bookName%")
    Page<Book> findByBookNameContaining(@Param("bookName") String bookName, Pageable pageable);

    //  @Procedure(name = "CheckRecords")
    // Object<?> checkRecords(Long userID, String isbn, Long inventoryID, String bookName, LocalDateTime borrowTime);

    List<Book> findByIsbnIn(List<String> isbnList);
}
