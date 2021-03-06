package model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class Transaction implements Entity {
    private Long id;
    private Long customerId;
    private Date date;
    private List<Product> products;
}
