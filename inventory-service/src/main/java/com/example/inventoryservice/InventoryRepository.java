package com.example.inventoryservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
    //Optional<Inventory> findBySkuCode(List<String> skuCode);

    List<Inventory> findBySkuCodeIn(List<String> skuCode);


}
