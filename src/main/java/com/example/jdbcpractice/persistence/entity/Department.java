package com.example.jdbcpractice.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Department {
    @Id
    private Integer id;

    @Column
    private String departmentName;

    @OneToMany (mappedBy="department", fetch=FetchType.EAGER)
    private List<Employee> employees;

    @OneToOne (mappedBy="department", fetch=FetchType.EAGER)
    private Manager manager;

}

