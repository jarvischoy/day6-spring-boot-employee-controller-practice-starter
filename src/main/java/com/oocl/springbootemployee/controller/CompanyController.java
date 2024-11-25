package com.oocl.springbootemployee.controller;

import com.oocl.springbootemployee.model.Company;
import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.repository.CompanyRepository;
import com.oocl.springbootemployee.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyRepository companyRepository;
//    private final EmployeeRepository employeeRepository;

    public CompanyController(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
//        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyRepository.findAllCompanies();
    }

//    @GetMapping("/{id}")
//    public Company getCompanyById(@PathVariable Long id) {
//        return companyRepository.findCompanyById(id);
//    }
//
//    @GetMapping("/{id}/employees")
//    public List<Employee> getEmployeesById(@PathVariable Long id) {
//        return employeeRepository.findByCompanyId(id);
//    }
//
//    @GetMapping(params = {"pageNumber", "pageSize"})
//    public List<Company> getByPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
//        return companyRepository.findByPage(pageNumber, pageSize);
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Company createCompany(@RequestBody Company company) {
//        return companyRepository.insert(company);
//    }
//
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Company updateCompany(@RequestBody Company company, @PathVariable Long id) {
//        return companyRepository.updateCompany(company, id);
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteCompany(@PathVariable Long id) {
//        companyRepository.deleteById(id);
//    }
}
