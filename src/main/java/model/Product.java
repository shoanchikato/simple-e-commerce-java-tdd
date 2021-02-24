package model;

import lombok.*;

@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
