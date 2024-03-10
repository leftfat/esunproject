package com.esunbank.Charlie.backend.common.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    private String isbn;

    private String bookName;

    private String author;

    private String introduction;
}
