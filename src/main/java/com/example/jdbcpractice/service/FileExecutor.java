package com.example.jdbcpractice.service;

import com.example.jdbcpractice.model.Employee;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class FileExecutor {
    private final static String FILE_PATH = "output.txt";
    private final static String OUTPUT_PATH = "news.csv";

    public List<Employee> readEmployeesFromFile() throws IOException {
        List<Employee> employeeList = new ArrayList<>();
        BufferedReader lineReader = new BufferedReader(new FileReader(OUTPUT_PATH));
        String lineText;
        while ((lineText = lineReader.readLine()) != null) {
            String[] data = lineText.split(",");
            String fName = data[1];
            String lName = data[2];
            String manId = data[3];
            String depId = data[4];
            String slr = data[5];

            Employee employee = new Employee();
            employee.setEmployeeFirstName(fName);
            employee.setEmployeeLastName(lName);
            employee.setManagerId(Integer.parseInt(manId));
            employee.setDepartmentId(Integer.parseInt(depId));
            employee.setEmployeeSalary(Integer.parseInt(slr));
            employeeList.add(employee);
        }
        return employeeList;
    }

    public void writeEmployeesToFile(List<Employee> employeeList) {
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
}


