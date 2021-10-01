package com.example.jdbcpractice;

import com.example.jdbcpractice.model.Employee;
import com.example.jdbcpractice.service.FileLoader;
import com.example.jdbcpractice.service.JDBCExecutor;
import com.example.jdbcpractice.service.WorkStructureConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JdbcPracticeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JdbcPracticeApplication.class, args);
    }

    @Override
    public void run(String... args) {
        var fileLoader = new FileLoader();
        List<Employee> employees = fileLoader.readFile("data/new-employees.csv");
        var jdbcExecutor = new JDBCExecutor();
        jdbcExecutor.saveEmployees(employees);
        String employeeStr = WorkStructureConverter.convertToCsvString(jdbcExecutor.findEmployees());
        fileLoader.writeToFile("data/all-employees.csv", employeeStr);

    }
}
