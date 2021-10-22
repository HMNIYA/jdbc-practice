package com.example.jdbcpractice.persistence.repository;

import com.example.jdbcpractice.persistence.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByManagerId(Integer id);

}
