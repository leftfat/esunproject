package com.esunbank.Charlie.backend.common.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.esunbank.Charlie.backend.common.domain.dto.InventoryDto;
import com.esunbank.Charlie.backend.common.domain.entity.Book;
import com.esunbank.Charlie.backend.common.domain.entity.BorrowingRecord;
import com.esunbank.Charlie.backend.common.domain.entity.Inventory;
import com.esunbank.Charlie.backend.common.domain.response.RecordResponse;
import com.esunbank.Charlie.backend.common.enumfolder.Status;

@Mapper(componentModel = "spring")
public interface InventoryConverter {

    Inventory toBean(InventoryDto dto);

    InventoryDto toDto(Inventory bean);
    @Mapping(source = "book.bookName", target = "bookName")
    @Mapping(source = "book.isbn", target = "isbn")
    RecordResponse toResponse(BorrowingRecord record, Book book);

    default Status StatusToInteger(Integer integer) {
        if (integer == null)
            return null;

        for (Status status : Status.values()) {
            if (status.getCode().equals(integer))
                return status;
        }

        return null;
    }

    default Integer IntegerToStatus(Status status) {
        if (status == null)
            return null;

        return status.getCode();
    }
}
