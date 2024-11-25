package com.oocl.springbootemployee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class EmployeeControllerTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private MockMvc client;

    @Autowired
    private JacksonTester<List<Employee>> jsonList;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        employeeRepository.reset();
    }

    @Test
    void should_return_employees_when_get_employees() throws Exception {
        // Given
        List<Employee> employees = employeeRepository.getAllEmployees();

        String jsonContent = jsonList.write(employees).getJson();

        // When
        client.perform(MockMvcRequestBuilders.get("/employees"))
                // Then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(4)))
                .andExpect(MockMvcResultMatchers.content().json(jsonContent));
    }

    @Test
    void should_return_employee_when_get_by_id_given_id() throws Exception {
        // Given

        // When
        client.perform(MockMvcRequestBuilders.post("/employees/1"))
                // Then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Lucy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value(Gender.FEMALE.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(8000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyId").value(1L));
        ;
    }

    @Test
    void should_return_male_employee_when_get_employees_given_gender() throws Exception {
        // Given
        List<Employee> employees = employeeRepository.getEmployeeByGender(Gender.MALE);

        // When
        client.perform(MockMvcRequestBuilders.get("/employees")
                        .param("gender", "MALE"))
                // Then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$..gender").value(everyItem(is(Gender.MALE.name()))));

    }

    @Test
    void should_create_employee_when_create_employee_given_json() throws Exception {
        // Given
        Employee newEmployee = new Employee(null, "Lulu", 23, Gender.FEMALE, 4000, 1L);

        // When
        client.perform(MockMvcRequestBuilders.post("/employees")
                        .content(objectMapper.writeValueAsString(newEmployee))
                        .contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Lulu"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(23))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value(Gender.FEMALE.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(4000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyId").value(1L));
        ;

        List<Employee> employeesAfterDeletion = employeeRepository.getAllEmployees();
        assertEquals(5, employeesAfterDeletion.size());

    }

    @Test
    void should_update_employee_when_update_given_id() throws Exception {
        // Given
        Employee updatedEmployee = new Employee(1L, "Lucy", 21, Gender.FEMALE, 8000, 1L);

        // When
        client.perform(MockMvcRequestBuilders.put("/employees/1")
                        .content(objectMapper.writeValueAsString(updatedEmployee))
                        .contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Lucy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(21))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value(Gender.FEMALE.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(8000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyId").value(1L));

    }

    @Test
    void should_return_204_when_perform_delete_given_employee_id() throws Exception {
        // Given
        // When
        client.perform(MockMvcRequestBuilders.delete("/employees/1"))
                // Then
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void should_return_employee_when_perform_get_given_page_number_and_size() throws Exception {
        // Given
        List<Employee> targetEmployee = new ArrayList<>();
        targetEmployee.add(new Employee(3L, "Lily", 22, Gender.FEMALE, 2800, 2L));
        targetEmployee.add(new Employee(4L, "Alice", 25, Gender.FEMALE, 3800, 3L));
        // When
        client.perform(MockMvcRequestBuilders.get("/employees")
                        .param("pageNumber", "2")
                        .param("pageSize", "2"))
                // Then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(targetEmployee)))
        ;
    }


}
