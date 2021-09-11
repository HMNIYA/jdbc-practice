package com.example.jdbcpractice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        var jdbcExecutor = new JDBCExecutor();
        // добавление сотрудников из файла
        jdbcExecutor.addEmployees();
        // вывод стркутуры департамента в файл
        jdbcExecutor.outputEmployeesToTxt();
    }
}