package com.esunbank.Charlie.backend.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esunbank.Charlie.backend.common.domain.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{
    List<Inventory> findByInventoryIdIn(List<Long> inventoryIdList);
}
