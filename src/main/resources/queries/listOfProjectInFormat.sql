#list of projects in the following format: creation date - project name - number of developers on this project
SELECT p.create_date, p.name, count(d.id)
FROM projects p, project_developer pd, developers d
WHERE p.id = pd.id_project AND d.id = pd.id_project
GROUP BY p.name;
