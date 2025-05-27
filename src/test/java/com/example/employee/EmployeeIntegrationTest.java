package com.example.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import com.example.model.Employee;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateAndGetEmployee() {
        Employee emp = new Employee(null, "Mark", "Sales", 7000.0);

        ResponseEntity<Employee> post = restTemplate.postForEntity("/api/employees", emp, Employee.class);
        assertEquals(HttpStatus.OK, post.getStatusCode());

        Employee created = post.getBody();
        assertNotNull(created);
        assertEquals("Mark", created.getName());

        ResponseEntity<Employee> get = restTemplate.getForEntity("/api/employees/" + created.getId(), Employee.class);
        assertEquals(HttpStatus.OK, get.getStatusCode());
        assertEquals("Mark", get.getBody().getName());
    }
}
