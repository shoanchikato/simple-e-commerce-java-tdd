package model;

import lombok.*;

@Data
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String surname;
    private int age;
}
