package jpa.dao;

import jpa.entity.Skill;

public interface SkillDao {
    boolean insertSkill(Skill skill);

    boolean updateSkill(Skill skill);

    Skill readSkill(Long id);

    boolean deleteSkill(Long id);
}
