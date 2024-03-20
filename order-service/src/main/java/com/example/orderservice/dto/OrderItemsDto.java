package com.example.orderservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
//OrderLineItemsDto might represent a simplified version of the order line items, containing only the
// necessary information such as SKU code, price, and quantity.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsDto {

    private String skuCode;

    private BigDecimal price;

    private Integer quantity;
}
