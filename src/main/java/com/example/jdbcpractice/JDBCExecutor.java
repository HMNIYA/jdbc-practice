package com.example.jdbcpractice;

import java.io.*;
import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.System.exit;

public class JDBCExecutor {
    String URL = "jdbc:h2:mem:test";
    String USER = "sa";
    String PASSWORD = "";

    public void saveEmployees(List<Employee> employeeList) {


        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = conn.createStatement();
            if (conn.isClosed()) statement = conn.createStatement();
            String sql = "select * from EMPLOYEE";
            ResultSet result = statement.executeQuery(sql);
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("output.csv"));
            while (result.next()) {
                int id = result.getInt("id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                int managerId = result.getInt("manager_id");
                int departmentId = result.getInt("department_id");
                int salary = result.getInt("salary");

                String line = String.format("%d,%s,%s,%d,%d,%d", id, firstName, lastName, managerId, departmentId, salary);
                fileWriter.write(line);
                fileWriter.newLine();
            }
            statement.close();
            fileWriter.close();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

    }

    public void findEmployees() {
        String csvFilePath = "news.csv";
//
//        String jdbcUrl = "jdbc:mysql://localhost:3306/ems";
//        String username = "root";
//        String password = "";
//        String filePath = "C:\\Users\\laser\\Desktop\\data.csv";
        int batchSize = 20;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
            String sql = "insert into EMPLOYEE(first_name,last_name,manager_id,department_id,salary) values(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText = null;
            int count = 0;
            while ((lineText = lineReader.readLine()) != null) {

                String[] data = lineText.split(",");

                //String id = data[0];
                String fName = data[1];
                String lName = data[2];
                String manId = data[3];
                String depId = data[4];
                String slr = data[5];

                //statement.setInt(1, parseInt(id));
                statement.setString(1, fName);
                statement.setString(2, lName);
                statement.setInt(3, parseInt(manId));
                statement.setInt(4, parseInt(depId));
                statement.setInt(5, parseInt(slr));
                statement.addBatch();
                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            lineReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();
            System.out.println("Data has been inserted successfully.");

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
//            return Collections.emptyList();
}
