package com.example.jdbcpractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class CSVReader {
    public static void main(String... args) {
        List<Employee> books = readEmployeeFromCSV("file_for_project.csv");
        for (Employee b : books) {
            System.out.println(b);
        }
    }

    private static List<Employee> readEmployeeFromCSV(String fileName) {
        List<Employee> books = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Employee book = createEmployee(attributes);
                books.add(book);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return books;
    }

    private static Employee createEmployee(String[] metadata) {
        int id = parseInt(metadata[0]);
        String firstName = metadata[1];
        String lastName = metadata[2];
        int managerId = parseInt(metadata[3]);
        int departmentId = parseInt(metadata[4]);
        int salary = parseInt(metadata[5]);

        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setManagerId(managerId);
        employee.setDepartmentId(departmentId);
        employee.setSalary(salary);

        return employee;
        }
}
