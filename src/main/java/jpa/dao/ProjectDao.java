package jpa.dao;

import jpa.entity.Project;

public interface ProjectDao {
    boolean insertProject(Project project);

    boolean updateProject(Project project);

    Project getByIdProject(Long id);

    boolean deleteProject(Long id);
}
