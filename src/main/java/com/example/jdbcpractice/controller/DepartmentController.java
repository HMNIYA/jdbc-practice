package com.example.jdbcpractice.controller;

import com.example.jdbcpractice.controller.dto.DepartmentDTO;
import com.example.jdbcpractice.controller.mapper.DepartmentMapper;
import com.example.jdbcpractice.persistence.entity.Department;
import com.example.jdbcpractice.persistence.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentRepository departmentRepository;

    @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentDTO> getDepartmentsWithEmployees() {
        return departmentRepository.findAll().stream()
                .map(DepartmentMapper::toDto)
                .collect(toList());
    }
}
