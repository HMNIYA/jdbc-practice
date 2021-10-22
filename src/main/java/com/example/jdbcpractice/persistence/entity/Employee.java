package com.example.jdbcpractice.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    private Integer id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @ManyToOne
    private Department department;

    @Column
    private Integer salary;

    @Column
    private Integer managerId;



}
