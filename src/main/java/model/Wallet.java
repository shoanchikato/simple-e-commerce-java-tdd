package model;

import lombok.*;

@Data
@AllArgsConstructor
public class Wallet {
    private Long id;
    private Long customerId;
    private double balance;
}
