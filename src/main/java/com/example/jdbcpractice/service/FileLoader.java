package com.example.jdbcpractice.service;

import com.example.jdbcpractice.model.Employee;
import com.example.jdbcpractice.model.WorkStructure;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileLoader {

    @SneakyThrows
    public List<Employee> readFile(String path) {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        }
        List<Employee> employeeList = new ArrayList<>();

        for (List<String> r : records) {
            employeeList.add(
                    Employee.builder()
                            .firstName(r.get(0))
                            .lastName(r.get(1))
                            .managerId(Integer.parseInt(r.get(2)))
                            .departmentId(Integer.parseInt(r.get(3)))
                            .salary(Double.parseDouble(r.get(4)))
                            .build()
            );
        }

        return employeeList;
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next().trim());
            }
        }
        return values;
    }

    @SneakyThrows
    public void writeToFile(String path, String str) {
        File file = new File(path);
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(str);
        }
    }
}
