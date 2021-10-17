package com.example.jdbcpractice.persistence.repository;

import com.example.jdbcpractice.persistence.entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void getEmployeesWithManagers() {
        List<Department> departmentList = departmentRepository.findAll();

        assertFalse(departmentList.isEmpty());

        Department department = departmentList.get(0);

        assertNotNull(department.getManager());
        assertFalse(department.getEmployees().isEmpty());
    }

}