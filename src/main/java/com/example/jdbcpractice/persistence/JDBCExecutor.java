package com.example.jdbcpractice.persistence;

import com.example.jdbcpractice.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.List;

@Component
public class JDBCExecutor {
    private final JdbcTemplate jdbcTemplate;

    public JDBCExecutor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getAllEmployees() {
        String query = "SELECT DISTINCT \n" +
                "d.department_name,\n" +
                "m.first_name as manager_first_name, \n" +
                "m.last_name as manager_last_name, \n" +
                "m.salary as manager_salary, \n" +
                "e.id as employee_id,\n" +
                "e.first_name as employee_first_name,\n" +
                "e.last_name as employee_last_name,\n" +
                "e.salary as employee_salary " +
                "FROM DEPARTMENT AS d\n" +
                "INNER JOIN \n" +
                "EMPLOYEE AS e\n" +
                "ON e.department_id=d.id\n" +
                "INNER JOIN\n" +
                "EMPLOYEE AS m\n" +
                "ON e.manager_id=m.id";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
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
    }

    public void addEmployees(List<Employee> employeeList) {
        String sql = "insert into EMPLOYEE(first_name,last_name,manager_id,department_id,salary) values(?,?,?,?,?)";
        for (Employee employee : employeeList) {
            jdbcTemplate.update(sql, ps -> {
                ps.setString(1, employee.getEmployeeFirstName());
                ps.setString(2, employee.getEmployeeLastName());
                ps.setInt(3, employee.getManagerId());
                ps.setInt(4, employee.getDepartmentId());
                ps.setInt(5, employee.getEmployeeSalary());
                ps.addBatch();
            });
        }
    }
}
