package com.oocl.springbootemployee.repository;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepository(){
        this.employees = new ArrayList<>();
        init();
    }

    public void reset(){
        employees.clear();
        init();
    }

    public void init() {
        employees.add(new Employee(1L, "Lucy", 20, Gender.FEMALE, 8000));
        employees.add(new Employee(2L, "Ben", 30, Gender.MALE, 2000));
        employees.add(new Employee(3L, "Lily", 22, Gender.FEMALE, 2800));
        employees.add(new Employee(4L, "Alice", 25, Gender.FEMALE, 3800));
    }


    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Employee getEmployeeById(Long id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Employee> getEmployeeByGender(Gender gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public Employee addEmployee(Employee employee) {
        employee.setId(generateId());
        employees.add(employee);
        return employee;
    }

    private Long generateId() {
        return employees.stream()
                .mapToLong(Employee::getId)
                .max()
                .orElse(0) + 1;
    }


    public Employee updateEmployee(Long id, Employee employee) {
        Employee targetEmployee = getEmployeeById(id);
        targetEmployee.setAge(employee.getAge());
        targetEmployee.setSalary(employee.getSalary());
        return targetEmployee;

    }


}
