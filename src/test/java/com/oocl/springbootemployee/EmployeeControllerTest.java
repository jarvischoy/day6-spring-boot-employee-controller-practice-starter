package com.oocl.springbootemployee;

import com.oocl.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class EmployeeControllerTest {
    final EmployeeRepository employeeRepository = new EmployeeRepository();

    @Autowired
    private MockMvc client;

    @Test
    void should_return_employees_when_get_employees() throws Exception {
        // Given

        // When
        client.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(employeeRepository.getEmployees().get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(employeeRepository.getEmployees().get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(employeeRepository.getEmployees().get(2).getId()));
        // Then
    }

    @Test
    void should_return_employee_when_get_by_id_given_id() throws Exception {
        // Given

        // When
        client.perform(MockMvcRequestBuilders.post("/employees/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(employeeRepository.getById(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(employeeRepository.getById(1).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(employeeRepository.getById(1).getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value(employeeRepository.getById(1).getGender().name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(employeeRepository.getById(1).getSalary()));

        // Then
    }


    



}
