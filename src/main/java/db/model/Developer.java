package db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Developer {
    private int id;
    private String name;
    private int age;
    private String gender;
    private double salary;
}
