package db.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class Project {
    private int id;
    private String name;
    private double cost;
    private Date date;
}
