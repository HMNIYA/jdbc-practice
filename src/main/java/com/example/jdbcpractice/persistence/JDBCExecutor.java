package com.example.jdbcpractice.persistence;

import com.example.jdbcpractice.model.Employee;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.List;

import static java.lang.Integer.parseInt;

@Component
public class JDBCExecutor {
    private final JdbcTemplate jdbcTemplate;
    private final static String FILE_PATH = "output.txt";
    private final static String OUTPUT_PATH = "news.csv";

    public JDBCExecutor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void outputEmployeesToTxt() {
        String query = "SELECT DISTINCT *\n" +
                "d.department_name,\n" +
                "m.first_name as manager_first_name, \n" +
                "m.last_name as manager_last_name, \n" +
                "m.salary as manager_salary, \n" +
                "e.id as employee_id,\n" +
                "e.first_name as employee_first_name,\n" +
                "e.last_name as employee_last_name,\n" +
                "e.salary as employee_salary " +
                "FROM DEPARTMENT AS d\n" +
                "INNER JOIN\n" +
                "EMPLOYEE AS e\n" +
                "ON e.department_id=d.id\n" +
                "INNER JOIN\n" +
                "EMPLOYEE AS m\n" +
                "ON e.manager_id=m.id WHERE e.manager_id IS NOT NULL";
        List<Employee> employeeList = jdbcTemplate.query(query, (rs, rowNum) -> {
            Employee employee = new Employee();
            employee.setDepartmentName(rs.getString("department_name"));
            employee.setManagerFirstName(rs.getString("manager_first_name"));
            employee.setManagerLastName(rs.getString("manager_last_name"));
            employee.setManagerSalary(rs.getInt("manager_salary"));
            employee.setEmployeeFirstName(rs.getString("employee_first_name"));
            employee.setEmployeeLastName(rs.getString("employee_last_name"));
            employee.setEmployeeSalary(rs.getInt("employee_salary"));
            return employee;
        });

        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH), StandardCharsets.UTF_8));
            String lastDepartment = "";
            String lastManager = "";
            for (Employee e : employeeList) {

                if (!(lastDepartment.equals(e.getDepartmentName()))) {
                    out.write("\nDepartment: " + e.getDepartmentName() + ": ");
                    lastDepartment = e.getDepartmentName();
                }
                if (!(lastManager.equals(e.getManagerFirstName() + e.getManagerLastName()))) {
                    out.write("\n        Manager: " + e.getManagerFirstName() + " " + e.getManagerLastName() + " (" + e.getManagerSalary() + "): ");
                    lastManager = e.getManagerFirstName() + e.getManagerLastName();
                }
                out.write("\n                Employee: " + e.getEmployeeFirstName() + " " + e.getEmployeeLastName() + " (" + e.getEmployeeSalary()+ "): ");
            }
            out.close();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public void addEmployees() {
        Connection connection = null;
        try {
            String sql = "insert into EMPLOYEE(first_name,last_name,manager_id,department_id,salary) values(?,?,?,?,?)";
            BufferedReader lineReader = new BufferedReader(new FileReader(OUTPUT_PATH));
            String lineText;
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String fName = data[1];
                String lName = data[2];
                String manId = data[3];
                String depId = data[4];
                String slr = data[5];
                jdbcTemplate.update(sql, ps -> {
                    ps.setString(1, fName);
                    ps.setString(2, lName);
                    ps.setInt(3, parseInt(manId));
                    ps.setInt(4, parseInt(depId));
                    ps.setInt(5, parseInt(slr));
                    ps.addBatch();
                });
            }
            lineReader.close();
            System.out.println("Data has been inserted successfully.");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
