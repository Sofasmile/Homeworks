package jpa.dao;

import jpa.entity.Developer;

public interface DeveloperDao {
    boolean insertDeveloper(Developer developer);

    boolean updateDeveloper(Developer developer);

    Developer readDeveloper(Long id);

    boolean deleteDeveloper(Long id);
}
