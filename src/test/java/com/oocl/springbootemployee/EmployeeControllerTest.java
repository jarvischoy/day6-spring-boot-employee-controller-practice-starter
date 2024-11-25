package com.oocl.springbootemployee;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.EmployeeRepository;
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

import java.util.List;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class EmployeeControllerTest {
    final EmployeeRepository employeeRepository = new EmployeeRepository();

    @Autowired
    private MockMvc client;

    @Autowired
    private JacksonTester<Employee> json;

    @Autowired
    private JacksonTester<List<Employee>> jsonList;

    @Test
    void should_return_employees_when_get_employees() throws Exception {
        // Given
        List<Employee> employees = employeeRepository.getEmployees();

        String jsonContent = jsonList.write(employees).getJson();

        // When
        client.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonContent));
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

    @Test
    void should_serialize_employee_to_json() throws Exception {
        // Given
        Employee employee = new Employee(1, "Sam1", 20, Gender.Male, 2000);

        // When
        String jsonContent = json.write(employee).getJson();

        // Then
        client.perform(MockMvcRequestBuilders.post("/employees/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonContent));
    }

    @Test
    void should_return_employee_when_get_employees_given_gender() throws Exception {
        // Given

        // When
        client.perform(MockMvcRequestBuilders.get("/employees")
                        .param("gender", "Male"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(2)));

        // Then
    }

    @Test
    void should_create_employee_when_create_employee_given_json() throws Exception {
        // Given
        String employeeJson = "{\n" +
                "        \"name\": \"Tony\",\n" +
                "        \"age\": 20,\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"salary\": 2000.0\n" +
                "    }";



        // When
        client.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Tony"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value(Gender.Male.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(2000.0));

        // Then
    }


}
