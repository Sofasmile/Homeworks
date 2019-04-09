package db.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Developer {
    private int id;
    private String name;
    private int age;
    private String gender;
    private double salary;

    public Developer(int id, String name, int age, String gender, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public Developer(String name, int age, String gender, double salary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public String toString() {
        return "Developer[" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", age = " + age +
                ", gender = '" + gender + '\'' +
                ", salary = " + salary +
                ']';
    }
}
