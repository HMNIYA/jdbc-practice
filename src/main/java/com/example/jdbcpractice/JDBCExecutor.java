package com.example.jdbcpractice;

import java.io.*;
import java.sql.*;
import java.util.List;

import static java.lang.Integer.parseInt;

public class JDBCExecutor {
    String URL = "jdbc:h2:mem:test";
    String USER = "sa";
    String PASSWORD = "";

    public void saveEmployees() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            stmt = conn.createStatement();
            File file = new File("output.txt");
            String query = "SELECT DISTINCT *\n" +
                    "FROM DEPARTMENT AS d\n" +
                    "INNER JOIN\n" +
                    "EMPLOYEE AS e\n" +
                    "ON e.department_id=d.id\n" +
                    "INNER JOIN\n" +
                    "EMPLOYEE AS e1\n" +
                    "ON e.manager_id=e1.id WHERE e.manager_id IS NOT NULL";
            ResultSet rs = stmt.executeQuery(query);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
                while (rs.next()) {
                    String departmentName = rs.getString(2);
                    String managerFirstName = rs.getString(10);
                    String managerLastName = rs.getString(11);
                    int managerSalary = rs.getInt(14);
                    String employeeFirstName = rs.getString(4);
                    String employeeLastName = rs.getString(5);
                    int employeeSalary = rs.getInt(8);
                    System.out.println(departmentName + ": " + managerFirstName + " " + managerLastName + " {" + managerSalary + "}: " + employeeFirstName + " " + employeeLastName + " {" + employeeSalary + "}");

//                    out.write(arg1 + ", ");
                }


            out.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public void addEmployees() {
        String csvFilePath = "news.csv";
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
                String fName = data[1];
                String lName = data[2];
                String manId = data[3];
                String depId = data[4];
                String slr = data[5];
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
}
