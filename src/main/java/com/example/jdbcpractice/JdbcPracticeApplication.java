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
        //TODO: в args первым аргументом передать файл в котором будет находится список Employee
        //TODO: сохранить новых Employee-в в соответстующиую таблицу
        var jdbcExecutor = new JDBCExecutor();
        jdbcExecutor.saveEmployees(List.of(new Employee()));

        //TODO:Вернуть всех Employee-в из таблицы Employee
        jdbcExecutor.findEmployees();
        //TODO:Сохранить в исходный файл информацию по каждому департаменту
        //TODO:Department: <название>
        //TODO:Manager: <ФИО> <ЗП> и список его подчиненных
        //TODO:Employee: <ФИО> <ЗП>
    }


}
