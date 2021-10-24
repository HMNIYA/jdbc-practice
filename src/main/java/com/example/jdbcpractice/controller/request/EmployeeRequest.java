package com.example.jdbcpractice.controller.request;

import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;

@Data
public class EmployeeRequest {
    int departmentId;
    int managerId;
    String lastName;
    String firstName;
    BigDecimal salary;
}
