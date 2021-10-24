package com.example.jdbcpractice.controller;

import com.example.jdbcpractice.controller.dto.EmployeeDTO;
import com.example.jdbcpractice.controller.request.EmployeeRequest;
import com.example.jdbcpractice.persistence.entity.Employee;
import com.example.jdbcpractice.persistence.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository employeeRepository;


    @GetMapping(value = "/{id}")
    public EmployeeDTO getEmployee(@PathVariable Long id) {
        return employeeRepository.findById(id).map(e ->
                EmployeeDTO.builder()
                        .firstName(e.getFirstName())
                        .lastName(e.getLastName())
                        .salary(e.getSalary())
                        .build())
                .orElse(EmployeeDTO.builder().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveEmployee(@RequestBody EmployeeRequest request) {
        employeeRepository.save(Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .salary(request.getSalary())
                .departmentId(request.getDepartmentId())
                .managerId(request.getManagerId())
                .build());
    }
}
