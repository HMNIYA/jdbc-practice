package com.example.jdbcpractice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @PostMapping("/employee")
    public void addEmployee() {

    }

    @GetMapping("/{id}")
    public void getEmployee(@PathVariable Integer id) {

    }
}
