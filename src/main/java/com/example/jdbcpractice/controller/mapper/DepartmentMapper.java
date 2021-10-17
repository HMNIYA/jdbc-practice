package com.example.jdbcpractice.controller.mapper;

import com.example.jdbcpractice.controller.dto.DepartmentDTO;
import com.example.jdbcpractice.controller.dto.EmployeeDTO;
import com.example.jdbcpractice.persistence.entity.Department;
import com.example.jdbcpractice.persistence.entity.Employee;
import com.example.jdbcpractice.persistence.entity.Manager;

import static java.util.stream.Collectors.toList;


public class DepartmentMapper {

    public static DepartmentDTO toDto(Department department) {
        return DepartmentDTO.builder()
                .name(department.getDepartmentName())
                .manager(buildEmployeeDTO(department.getManager()))
                .employees(
                        department.getEmployees().stream()
                                .map(DepartmentMapper::buildEmployeeDTO)
                                .collect(toList())
                )
                .build();
    }

    private static EmployeeDTO buildEmployeeDTO(Employee e) {
        return EmployeeDTO.builder()
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .salary(e.getSalary())
                .build();
    }

    private static EmployeeDTO buildEmployeeDTO(Manager m) {
        return EmployeeDTO.builder()
                .firstName(m.getFirstName())
                .lastName(m.getLastName())
                .salary(m.getSalary())
                .build();
    }
}
