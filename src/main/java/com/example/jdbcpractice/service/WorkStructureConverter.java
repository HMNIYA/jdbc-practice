package com.example.jdbcpractice.service;

import com.example.jdbcpractice.model.Department;
import com.example.jdbcpractice.model.Employee;
import com.example.jdbcpractice.model.Manager;
import com.example.jdbcpractice.model.WorkStructure;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

public class WorkStructureConverter {


    public static String convertToCsvString(List<WorkStructure> wsList) {
        Map<Department, Map<Manager, List<Employee>>> groupedMap = wsList.stream()
                .collect(groupingBy(WorkStructureConverter::getDepartment,
                        groupingBy(WorkStructureConverter::getManager,
                                mapping(WorkStructureConverter::getEmployee, toList()))));

        StringBuilder sb = new StringBuilder();
        Set<Department> departments = groupedMap.keySet();
        for (Department d : departments) {
            sb.append("\tdepartment: ");
            sb.append(convertDepartmentToCSV(d));
            Map<Manager, List<Employee>> managersWithEmployees = groupedMap.get(d);
            for (Manager m : managersWithEmployees.keySet()) {
                sb.append("\n");
                sb.append("\t\tmanager: ");
                sb.append(convertManagerToCSV(m));
                for (Employee e : managersWithEmployees.get(m)) {
                    sb.append("\n");
                    sb.append("\t\t\temployee: ");
                    sb.append(convertEmployeeToCSV(e));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static Department getDepartment(WorkStructure w) {
        return Department.builder()
                .id(w.getDepartmentId())
                .departmentName(w.getDepartmentName())
                .build();
    }

    private static Manager getManager(WorkStructure w) {
        return Manager.builder()
                .id(w.getManagerId())
                .firstName(w.getManagerFirstName())
                .lastName(w.getManagerLastName())
                .build();
    }

    private static Employee getEmployee(WorkStructure w) {
        return Employee.builder()
                .id(w.getEmployeeId())
                .firstName(w.getEmployeeFirstName())
                .lastName(w.getEmployeeLastName())
                .salary(w.getEmployeeSalary())
                .build();
    }

    public static String convertDepartmentToCSV(Department d) {
        return String.join(",", new String[]{
                " id: " + d.getId(),
                " name: " + d.getDepartmentName(),
        });
    }

    public static String convertManagerToCSV(Manager m) {
        return String.join(",", new String[]{
                " id: " + m.getId(),
                " first name: " + m.getFirstName(),
                " last name: " + m.getLastName()
        });
    }

    public static String convertEmployeeToCSV(Employee e) {
        return String.join(",", new String[]{
                " id: " + e.getId(),
                " first name: " + e.getFirstName(),
                " last name: " + e.getLastName(),
                " salary: " + e.getSalary()
        });
    }
}
