package db.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Customer {
    private int id;
    private String name;
    private int age;

    public Customer(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "Customer[" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", age = '" + age + '\'' +
                ']';
    }
}
