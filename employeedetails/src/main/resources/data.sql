
DROP TABLE IF EXISTS EMPLOYEE;


CREATE TABLE employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  salary DOUBLE(10) NOT NULL
);

insert into employee (first_name, last_name, salary)
values ('Rohit', 'Kumar', 20000);

insert into employee (first_name, last_name, salary)
values ('Vishal', 'Khanna', 25000);

insert into employee (first_name, last_name, salary)
values ('Sagar', 'Deshpande', 28000);

insert into employee (first_name, last_name, salary)
values ('Salman', 'Shaikh', 40000);

insert into employee (first_name, last_name, salary)
values ('Rohan', 'Thomas', 35000);

insert into employee (first_name, last_name, salary)
values ('Manasi', 'Shinde', 38000);

insert into employee (first_name, last_name, salary)
values ('Anvay', 'Kulkarni', 50000);

insert into employee (first_name, last_name, salary)
values ('Akshay', 'Shetty', 48000);

insert into employee (first_name, last_name, salary)
values ('Himani', 'Shah', 36000);

insert into employee (first_name, last_name, salary)
values ('Ria', 'Sharma', 44000);