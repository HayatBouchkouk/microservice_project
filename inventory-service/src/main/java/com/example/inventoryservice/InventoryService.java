package com.example.inventoryservice;


import com.example.inventoryservice.dto.InventoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;


  /* output : [
          {skuCode='SKU123', isInStock=true},
         {skuCode='SKU456', isInStock=false},
         {skuCode='SKU789', isInStock=true}
]*/


        @Transactional(readOnly = true)
        public List<InventoryResponse> isInStock(List<String> skuCodes) {
            log.info("Checking Inventory for SKU codes: {}", skuCodes);

            // Retrieve inventory entries for the given SKU codes
            List<Inventory> inventoryEntries = inventoryRepository.findBySkuCodeIn(skuCodes);

            // Create a map to store inventory status for each SKU code
            Map<String, Boolean> inventoryStatusMap = new HashMap<>();

            // Populate the inventory status map
            for (String skuCode : skuCodes) {
                // Check if an inventory entry exists for the SKU code
                boolean isInStock = inventoryEntries.stream()
                        .anyMatch(inventory -> inventory.getSkuCode().equals(skuCode) && inventory.getQuantity() > 0);
                inventoryStatusMap.put(skuCode, isInStock);
            }

            // Build InventoryResponse objects based on the inventory status map
            List<InventoryResponse> inventoryResponses = skuCodes.stream()
                    .map(skuCode -> InventoryResponse.builder()
                            .skuCode(skuCode)
                            .isInStock(inventoryStatusMap.getOrDefault(skuCode, false)) // Default to false if SKU code not found
                            .build())
                    .collect(Collectors.toList());

            return inventoryResponses;
        }


    }

