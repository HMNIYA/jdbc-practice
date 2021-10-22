package com.example.jdbcpractice.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Where(clause = "manager_id IS NULL")
@Table(name = "EMPLOYEE")
@Getter
@Setter
public class Manager {
    @Id
    private Integer id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Integer salary;

    @OneToOne
    private Department department;

}
