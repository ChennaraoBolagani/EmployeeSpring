package com.example.employee;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

     @InjectMocks
    private EmployeeService service;

    @Test
    void shouldReturnAllEmployees() {
        List<Employee> employees = Arrays.asList(
            new Employee(1L, "Alice", "HR", 4000.0)
        );

        when(repository.findAll()).thenReturn(employees);

        Iterable<Employee> result = service.getAllEmployees();

        List<Employee> resultList = StreamSupport
            .stream(result.spliterator(), false)
            .collect(Collectors.toList());

        assertEquals(1, resultList.size());
        assertEquals("Alice", resultList.get(0).getName());

        verify(repository, times(1)).findAll();
    }
}


