package com.esunbank.Charlie.backend.common.domain.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordResponse {
    private String isbn;
    private Long inventoryId;
    private LocalDateTime borrowTime;
    private String bookName;

}
