package db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Skill {
    private int id;
    private String industry;
    private String level;
}
