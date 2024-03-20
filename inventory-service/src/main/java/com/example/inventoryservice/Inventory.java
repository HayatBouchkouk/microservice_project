package com.example.inventoryservice;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_inventory")
public class Inventory {

    @Id
    @GeneratedValue
    private Integer id;// Unique identifier for the product.
    private String skuCode;
    private Integer quantity;//The quantity of the product available in stock.

}
