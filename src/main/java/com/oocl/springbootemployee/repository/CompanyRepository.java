package com.oocl.springbootemployee.repository;

import com.oocl.springbootemployee.model.Company;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class CompanyRepository {

    private final List<Company> companies;

    public CompanyRepository(){
        companies = new ArrayList<>();
        companies.add(new Company(1L, "company1"));
        companies.add(new Company(2L, "company2"));
        companies.add(new Company(3L, "company3"));
    }

    public List<Company> findAllCompanies() {
        return companies;
    }

// Untested Code
//    public Company findCompanyById(Long id) {
//        return companies.stream()
//                .filter(company -> Objects.equals(company.getId(), id))
//                .findFirst()
//                .orElse(null);
//    }
//
//    public List<Company> findByPage(int pageNumber, int pageSize) {
//        return companies.stream()
//                .skip((long) (pageNumber - 1) * pageSize)
//                .limit(pageSize)
//                .collect(Collectors.toList());
//    }
//
//    public Company insert(Company company) {
//        company.setId(generateId());
//        companies.add(company);
//        return company;
//    }
//
//    private Long generateId() {
//        return companies.stream()
//                .mapToLong(Company::getId)
//                .max()
//                .orElse(0L)
//                + 1;
//    }
//
//    public Company updateCompany(Company company, Long id) {
//        Company targetCompany = findCompanyById(id);
//
//        if(Objects.isNull(targetCompany)) {
//            return null;
//        }
//        targetCompany.setName(!Objects.isNull(company.getName())? company.getName(): targetCompany.getName());
//        return targetCompany;
//    }
//
//    public void deleteById(Long id) {
//        companies.removeIf(company -> Objects.equals(company.getId(), id));
//    }
}

