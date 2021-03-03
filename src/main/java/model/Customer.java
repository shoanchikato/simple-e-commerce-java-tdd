package model;

import lombok.*;

@Data
@AllArgsConstructor
public class Customer implements Entity {
    private Long id;
    private String name;
    private String surname;
    private int age;
}
