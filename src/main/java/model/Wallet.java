package model;

import lombok.*;

@Data
@AllArgsConstructor
public class Wallet implements Entity {
    private Long id;
    private Long customerId;
    private double balance;
}
