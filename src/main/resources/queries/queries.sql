#Добавить разработчикам поле (salary - зарплата).
alter table developers
  add salary int not null;

#Найти самый дорогой проект (исходя из salary всех разработчиков).
select *
from projects
       inner join project_developer on projects.id = project_developer.id_project
       inner join developers on project_developer.id_developer = developers.id
group by projects.id
order by sumSalary DESC
LIMIT 1;

#Вычислить общую ЗП только Java разработчиков.
select sum(salary)
from developers
       inner join skills on developers.developer_id = skills.developer_id
where skills.industry = "JAVA";

#Добавить поле (cost - стоимость) в таблицу Projects .
alter table projects
  add cost  int not null;

#Найти самый дешевый проект (исходя из cost всех проектов).
select p.name
from projects p
where p.cost = (select MIN(cost) from projects);

#Вычислить среднюю ЗП программистов в самом дешевом проекте.
select AVG(d.salary)
from developers d, project_developer pd, projects p
where d.id = pd.id_developer and p.id = pd.id_project and p.cost = (select MIN(cost) from projects);
