#Add developers field (salary - salary).
ALTER TABLE developers
  ADD salary INT NOT NULL ;

#Find the most expensive project (based on the salary of all developers).
SELECT *
FROM projects
INNER JOIN project_developer ON projects.id = project_developer.id_project
INNER JOIN developers ON project_developer.id_developer = developers.id
GROUP BY projects.id
ORDER BY sumSalary DESC
LIMIT 1;

#Calculate total GP of only Java developers.
SELECT sum(salary)
FROM developers
INNER JOIN skills ON developers.developer_id = skills.developer_id
WHERE skills.industry = "JAVA";

#Add a field (cost) to the Projects table.
ALTER TABLE projects
ADD cost  INT NOT NULL ;

#Find the cheapest project (based on the cost of all projects).
SELECT p.name
FROM projects p
WHERE p.cost = (SELECT MIN(cost) FROM projects);

#Calculate the average salary of programmers in the cheapest project.
SELECT AVG(d.salary)
FROM developers d, project_developer pd, projects p
WHERE d.id = pd.id_developer AND p.id = pd.id_project AND p.cost = (SELECT MIN(cost) FROM projects);
