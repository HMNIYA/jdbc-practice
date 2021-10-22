package com.example.jdbcpractice.controller;

import com.example.jdbcpractice.controller.dto.DepartmentDTO;
import com.example.jdbcpractice.controller.dto.EmployeeDTO;
import com.example.jdbcpractice.persistence.entity.Department;
import com.example.jdbcpractice.persistence.entity.Employee;
import com.example.jdbcpractice.persistence.entity.Manager;
import com.example.jdbcpractice.persistence.repository.DepartmentRepository;
import com.example.jdbcpractice.persistence.repository.EmployeeRepository;
import com.example.jdbcpractice.persistence.repository.ManagerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ManagerRepository managerRepository;

    public DepartmentController(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, ManagerRepository managerRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.managerRepository = managerRepository;
    }

    @GetMapping("/employees")
    public List<DepartmentDTO> getAllEmployees() {
        List<Department> allDepartments = departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOs = new ArrayList<>();

        for (int i = 0; i < allDepartments.size(); i++) {
            Department department = allDepartments.get(i);
            Manager manager = department.getManager();
            List<EmployeeDTO> employeeDTO = new ArrayList<>();

            EmployeeDTO managerDTO = EmployeeDTO
                    .builder()
                    .firstName(manager.getFirstName())
                    .lastName(manager.getLastName())
                    .salary(manager.getSalary())
                    .build();

            List<Employee> employeesForDepartment = employeeRepository.findByManagerId(manager.getId());

            for (int j = 0; j < employeesForDepartment.size(); j++) {
                    employeeDTO.add(j, EmployeeDTO
                            .builder()
                            .firstName(employeesForDepartment.get(j).getFirstName())
                            .lastName(employeesForDepartment.get(j).getLastName())
                            .salary(employeesForDepartment.get(j).getSalary())
                            .build());
            }
            departmentDTOs.add(i, DepartmentDTO.builder().name(department.getDepartmentName()).manager(managerDTO).employees(employeeDTO).build());
        }
        return departmentDTOs;
    }

    @PostMapping("/{id}")
    public void indexingSalary(@PathVariable Integer id, @RequestParam int sum) {

    }

}
