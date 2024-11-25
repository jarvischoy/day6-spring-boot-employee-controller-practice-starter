package com.oocl.springbootemployee.controller;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
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
        return employeeRepository.getAllEmployees();
    }

    @PostMapping(path = "/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.getEmployeeById(id);
    }

    @GetMapping(params = "gender")
    public List<Employee> getEmployeesByGender(@RequestParam Gender gender) {
        return employeeRepository.getEmployeeByGender(gender);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeRepository.addEmployee(employee);
    }

    @PutMapping(path = "/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        return employeeRepository.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteEmployeeById(id);
    }

}
