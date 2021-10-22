package com.example.jdbcpractice.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class DepartmentDTO {
    String name;
    EmployeeDTO manager;
    List<EmployeeDTO> employees;
}
