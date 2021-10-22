package com.example.jdbcpractice.persistence.repository;

import com.example.jdbcpractice.persistence.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
}
