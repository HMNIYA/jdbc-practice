DROP TABLE IF EXISTS EMPLOYEE;
DROP TABLE IF EXISTS DEPARTMENT;

CREATE TABLE DEPARTMENT (
    id INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(250) NOT NULL
);

CREATE TABLE EMPLOYEE (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  manager_id NUMBER DEFAULT NULL,
  department_id NUMBER,
  salary NUMBER DEFAULT NULL,
  foreign key (department_id) references DEPARTMENT(id)
);

INSERT INTO DEPARTMENT (department_name) VALUES
('development'),
('DevOps');

INSERT INTO EMPLOYEE (first_name, last_name, department_id, salary) VALUES
('Aliko', 'Dangote', 1, 500000),
('Bill', 'Gates', 2, 500000);

INSERT INTO EMPLOYEE (first_name, last_name, department_id, manager_id, salary) VALUES
('Ivan', 'Drago', 1, 1, 200000),
('Ivan', 'Ivanov', 1, 1, 100000),
('Ivan', 'Sidorov', 2, 2, 100000);