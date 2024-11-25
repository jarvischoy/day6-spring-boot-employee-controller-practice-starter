package com.oocl.springbootemployee.repository;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        this.employees.add(new Employee(1, "Sam1", 20, Gender.Male, 2000));
        this.employees.add(new Employee(2, "Sam2", 20, Gender.Male, 2000));
        this.employees.add(new Employee(3, "Sam3", 20, Gender.Male, 2000));
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getById(int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElseThrow();
    }
}
