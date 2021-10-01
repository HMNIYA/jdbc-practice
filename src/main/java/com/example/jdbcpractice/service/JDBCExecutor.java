package com.example.jdbcpractice.service;

import com.example.jdbcpractice.model.Employee;
import com.example.jdbcpractice.model.WorkStructure;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCExecutor {
    @SneakyThrows
    public void saveEmployees(List<Employee> employeeList) {
        String updatePositionSql = "INSERT INTO EMPLOYEE(first_name, last_name, manager_id, department_id, salary) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(updatePositionSql)) {
            for (Employee e : employeeList) {
                pstmt.setString(1, e.getFirstName());
                pstmt.setString(2, e.getLastName());
                pstmt.setInt(3, e.getManagerId());
                pstmt.setInt(4, e.getDepartmentId());
                pstmt.setDouble(5, e.getSalary());

                pstmt.executeUpdate();
            }
        }
    }

    @SneakyThrows
    public List<WorkStructure> findEmployees() {
        Statement stmt = getConnection().createStatement();
        String query = "SELECT \n" +
                "d.id as department_id,\n" +
                "d.department_name,\n" +
                "m.id as manager_id, \n" +
                "m.first_name as manager_first_name, \n" +
                "m.last_name as manager_last_name, \n" +
                "e.id as employee_id,\n" +
                "e.first_name as employee_first_name,\n" +
                "e.last_name as employee_last_name,\n" +
                "e.salary as employee_salary \n" +
                "FROM EMPLOYEE e\n" +
                "join EMPLOYEE m\n" +
                "on e.manager_id = m.id\n" +
                "join DEPARTMENT d\n" +
                "on d.id = e.department_id and d.id = m.department_id";

        try (ResultSet resultSet = stmt.executeQuery(query)) {
            List<WorkStructure> workStructures = new ArrayList<>();
            while (resultSet.next()) {
                workStructures.add(
                        WorkStructure.builder()
                                .departmentId(resultSet.getInt("department_id"))
                                .departmentName(resultSet.getString("department_name"))
                                .managerId(resultSet.getInt("manager_id"))
                                .managerFirstName(resultSet.getString("manager_first_name"))
                                .managerLastName(resultSet.getString("manager_last_name"))
                                .employeeId(resultSet.getInt("employee_id"))
                                .employeeFirstName("employee_first_name")
                                .employeeLastName("employee_last_name")
                                .employeeSalary(resultSet.getDouble("employee_salary"))
                                .build()
                );
            }
            return workStructures;
        }
    }

    private static Connection getConnection() throws SQLException, IOException {
        var props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("src/main/resources/application.properties"))) {
            props.load(in);
        }
        String drivers = props.getProperty("spring.datasource.driverClassName");
        if (drivers != null) System.setProperty("jdbc.drivers", drivers);
        String url = props.getProperty("spring.datasource.url");
        String username = props.getProperty("spring.datasource.username");
        String password = props.getProperty("spring.datasource.password");

        return DriverManager.getConnection(url, username, password);
    }
}
