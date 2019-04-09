#зарплату(сумму) всех разработчиков отдельного проекта
SELECT SUM(developers.salary)
FROM developers
INNER JOIN project_developer
ON developers.id = project_developer.id_developer
INNER JOIN projects
ON project_developer.id_project = projects.id
WHERE projects.name = ?;
