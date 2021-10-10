package com.example.jdbcpractice.persistence.jdbc;

import com.example.jdbcpractice.persistence.jdbc.JDBCExecutor;
import com.example.jdbcpractice.persistence.jdbc.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JDBCExecutorTest {
    @Autowired
    JDBCExecutor jdbcExecutor;

    @Test
    void getAllEmployees() {
        List<Employee> allEmployees = jdbcExecutor.getAllEmployees();
        Assertions.assertFalse(allEmployees.isEmpty());
    }

}