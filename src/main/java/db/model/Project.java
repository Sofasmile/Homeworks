package db.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class Project {
    private int id;
    private String name;
    private double cost;
    private Date date;

    public Project() {
    }

    public Project(int id, String name, double cost, Date date) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.date = date;
    }

    public Project(String name, double cost, Date date) {
        this.name = name;
        this.cost = cost;
        this.date = date;
    }

    public String toString() {
        return "Project[" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", date = '" + date + '\'' +
                ']';
    }
}
