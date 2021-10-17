package com.example.jdbcpractice.persistence.repository;

import com.example.jdbcpractice.persistence.entity.Department;
import com.example.jdbcpractice.persistence.entity.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @EntityGraph(attributePaths = {"employees", "manager"})
    List<Department> findAll();
}
