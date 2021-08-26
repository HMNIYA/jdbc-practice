package com.example.jdbcpractice;

import java.io.*;
import java.sql.*;
import java.util.List;
import static java.lang.Integer.parseInt;

public class JDBCExecutor {
    String URL = "jdbc:h2:mem:test";
    String USER = "sa";
    String PASSWORD = "";

    public void saveEmployees(List<Employee> employeeList) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs;
            stmt = conn.createStatement();
            File file = new File("output.txt");
            String query = "SELECT first_name,last_name,salary FROM EMPLOYEE WHERE (department_id = 1)";
            rs = stmt.executeQuery(query);
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), "UTF-8"));
            while (rs.next()) {
                String arg1 = rs.getString(1);
                String arg2 = rs.getString(2);
                String arg3 = rs.getString(3);
                System.out.println(arg1 + ", " + arg2 + ", " + arg3);
                out.write(arg1 + ", ");
                out.write(arg2 + ", ");
                out.write(arg3 + "\n");
            }
            out.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public void findEmployees() {
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
