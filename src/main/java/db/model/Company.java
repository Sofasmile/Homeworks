package db.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Company {
    private int id;
    private String name;
    private String address;

    public Company(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Company(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String toString() {
        return "Project[" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", address = '" + address + '\'' +
                ']';
    }

}
