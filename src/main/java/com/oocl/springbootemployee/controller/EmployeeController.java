package com.oocl.springbootemployee.controller;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

//    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping()
    public List<Employee> getEmployees() {
        return employeeRepository.getEmployees();
    }

    @PostMapping(path = "/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeRepository.getById(id);
    }
}
