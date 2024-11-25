package com.oocl.springbootemployee.model;

import java.io.Serializable;

public class Employee implements Serializable {

    private Long id;
    private String name;
    private int age;
    private Gender gender;
    private double salary;
    private Long companyId;

    public Employee(Long id, String name, int age, Gender gender, double salary, Long companyId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
