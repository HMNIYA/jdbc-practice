package com.example.jdbcpractice.persistence.repository;

import com.example.jdbcpractice.persistence.entity.Department;
import com.example.jdbcpractice.persistence.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
