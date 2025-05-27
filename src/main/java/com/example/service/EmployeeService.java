package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.EmployeeNotFoundException;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public Iterable<Employee> getAllEmployees() {
        return repository.findAll();
    }


    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee addEmployee(Employee emp) {
        return repository.save(emp);
    }

    public Employee updateEmployee(Long id, Employee emp) {
        Employee existing = getEmployeeById(id);
        existing.setName(emp.getName());
        existing.setDepartment(emp.getDepartment());
        existing.setSalary(emp.getSalary());
        return repository.save(existing);
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
