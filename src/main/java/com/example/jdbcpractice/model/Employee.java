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
public class Employee {
    int id;
    String firstName;
    String lastName;
    int managerId;
    int departmentId;
    double salary;
}
