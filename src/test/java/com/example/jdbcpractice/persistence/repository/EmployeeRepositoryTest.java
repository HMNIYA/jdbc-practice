package com.example.jdbcpractice.persistence.repository;

import com.example.jdbcpractice.persistence.entity.Department;
import com.example.jdbcpractice.persistence.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void getEmployeesWithManagers() {
        List<String> departmentList = employeeRepository.findAll().stream()
                .map(Employee::getDepartment)
                .map(Department::getDepartmentName)
                .collect(toList());
        Assertions.assertFalse(departmentList.isEmpty());
    }

    @Test
    void getDepartmentsWithAllEmployees() {
        List<Department> departments = departmentRepository.findAll();
        List<Employee> employeeList = departments.stream()
                .map(Department::getEmployeeList)
                .flatMap(Collection::stream)
                .collect(toList());
        Assertions.assertFalse(employeeList.isEmpty());
    }
}