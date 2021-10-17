package com.example.jdbcpractice.controller.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@JsonRootName("department")
public class DepartmentDTO {
    String name;
    EmployeeDTO manager;
    List<EmployeeDTO> employees;

}
