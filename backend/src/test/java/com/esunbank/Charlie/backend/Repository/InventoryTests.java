package com.esunbank.Charlie.backend.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import com.esunbank.Charlie.backend.common.converter.InventoryConverter;
import com.esunbank.Charlie.backend.common.domain.dto.InventoryDto;
import com.esunbank.Charlie.backend.common.domain.entity.Inventory;
import com.esunbank.Charlie.backend.common.enumfolder.Status;

@SpringBootTest
public class InventoryTests {
    @Autowired
    private InventoryConverter inventoryConverter;

    @Test
    public void test() {
        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setInventoryId(1L);
        inventoryDto.setIsbn("1234567890");
        inventoryDto.setStatus(Status.BEING_SORTED);
        inventoryDto.setStoreTime(null);
        Inventory statusToInteger = inventoryConverter.toBean(inventoryDto);
        System.err.println(statusToInteger);
        InventoryDto dto = inventoryConverter.toDto(statusToInteger);
        System.err.println(dto);
    }
   
    @Test
    void contextLoads() {
        String password = "123";
        String md5 = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(md5);
    }
}

