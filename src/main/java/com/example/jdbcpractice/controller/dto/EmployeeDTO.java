package com.example.jdbcpractice.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class EmployeeDTO {
    String lastName;
    String firstName;
    BigDecimal salary;
}
