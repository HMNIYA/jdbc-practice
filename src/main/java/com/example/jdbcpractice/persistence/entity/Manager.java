package com.example.jdbcpractice.persistence.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Getter
@Entity
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "EMPLOYEE")
@Where(clause = "manager_id is null")
public class Manager {
    @Id
    @GeneratedValue
    Long id;
    String firstName;
    String lastName;
    BigDecimal salary;

    @ManyToOne(fetch = FetchType.LAZY)
    Department department;
}
