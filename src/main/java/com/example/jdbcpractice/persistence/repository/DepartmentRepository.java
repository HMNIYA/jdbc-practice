package com.example.jdbcpractice.persistence.repository;

import com.example.jdbcpractice.persistence.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {




}
