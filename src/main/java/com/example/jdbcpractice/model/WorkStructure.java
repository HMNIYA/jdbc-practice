package com.example.jdbcpractice.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkStructure {
    int departmentId;
    String departmentName;
    int managerId;
    String managerFirstName;
    String managerLastName;
    int employeeId;
    String employeeFirstName;
    String employeeLastName;
    double employeeSalary;
}
