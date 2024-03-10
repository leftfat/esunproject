package com.esunbank.Charlie.backend.common.domain.dto;

import java.time.LocalDateTime;

import com.esunbank.Charlie.backend.common.enumfolder.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class InventoryDto {
    
    private Long inventoryId;

    private String isbn;

    private Status status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime storeTime;
    

}
