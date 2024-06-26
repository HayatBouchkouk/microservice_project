package com.example.inventoryservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class InventoryResponse {

    public String skuCode;
    private boolean isInStock;
}
