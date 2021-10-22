package com.example.jdbcpractice.persistence.repository;

import com.example.jdbcpractice.persistence.entity.Department;
import com.example.jdbcpractice.persistence.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

//    @Test
//    void getAllEmployees() {
//        List<Department> allDepartment = departmentRepository.findAll();
//        Department department = allDepartment.get(0);
//        List<Employee> employees = department.getEmployees();
//
//        Assertions.assertFalse(allDepartment.isEmpty());
//        Assertions.assertFalse(employees.isEmpty());
//        Assertions.assertNotNull(employees.get(0));
//    }

}