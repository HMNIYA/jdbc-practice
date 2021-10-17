package com.example.jdbcpractice.persistence.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Getter
@EqualsAndHashCode
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    @Id
    @GeneratedValue
    Long id;
    String firstName;
    String lastName;
    BigDecimal salary;

    @ManyToOne(fetch = FetchType.LAZY)
    Department department;
}
