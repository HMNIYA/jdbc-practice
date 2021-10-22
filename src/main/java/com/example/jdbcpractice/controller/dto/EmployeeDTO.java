package com.example.jdbcpractice.controller.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class EmployeeDTO {
    private String lastName;
    private String firstName;
    private Integer salary;
}
