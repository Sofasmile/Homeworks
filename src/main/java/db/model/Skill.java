package db.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Skill {
    private int id;
    private String industry;
    private String level;

    public Skill(int id, String industry, String level) {
        this.id = id;
        this.industry = industry;
        this.level = level;
    }

    public Skill(String industry, String level) {
        this.industry = industry;
        this.level = level;
    }

    public String toString() {
        return "Skill[" +
                "id = " + id +
                ", industry = '" + industry + '\'' +
                ", level = " + level +
                ']';
    }
}
