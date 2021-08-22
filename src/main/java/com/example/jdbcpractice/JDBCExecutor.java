package com.example.jdbcpractice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static java.lang.System.exit;

public class JDBCExecutor {
    public void saveEmployees(List<Employee> employeeList) {
        String URL = "jdbc:h2:mem:test";
        String USER = "sa";
        String PASSWORD = "";

        try (Connection conn = DriverManager
                .getConnection(URL, USER, PASSWORD);) {
            Statement statement = conn.createStatement();
            //ResultSet res = statement.executeQuery("select * from EMPLOYEE");
            //FileWriter fw = new FileWriter("file_for_project.csv");
            if(conn.isClosed()) statement = conn.createStatement();
            String sql = "select * from EMPLOYEE";

            ResultSet result = statement.executeQuery(sql);

            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("file_for_project.csv"));

            // write header line containing column names
            //fileWriter.write("first_name,last_name,manager_id,department_id,salary");

            while (result.next()) {
                int id = result.getInt("id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                int managerId = result.getInt("manager_id");
                int departmentId = result.getInt("department_id");
                int salary = result.getInt("salary");

                String line = String.format("%d,%s,%s,%d,%d,%d",id, firstName, lastName, managerId, departmentId, salary);
                fileWriter.write(line);
                fileWriter.newLine();

            }

            statement.close();
            fileWriter.close();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

    }

        public List<Employee> findEmployees() {
            return Collections.emptyList();
    }
}
