package com.challenge.api.service;

import com.challenge.api.model.Employee;
import com.challenge.api.model.SampleEmployee;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {

    private final Map<UUID, Employee> employees = new ConcurrentHashMap<>();

    public EmployeeService() {
        // creating mock employees for later testing
        addMockEmployee("Ajay", "Sommi", 105000, 22, "Software Engineer", "ajay.sommi@mockcompany.com");
        addMockEmployee("John", "Doe", 120000, 45, "Analyst", "john.doe@mockcompany.com");
        addMockEmployee("Mark", "Phillips", 780000, 28, "Analyst", "mark.phillips@mockcompany.com");
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public Employee getEmployeeByUuid(UUID uuid) {
        return employees.get(uuid);
    }

    public Employee createEmployee(SampleEmployee employee) {
        if (employee.getFirstName() == null || employee.getLastName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "firstName and lastName are required");
        }
        employee.setUuid(UUID.randomUUID());
        employee.setFullName(employee.getFirstName() + " " + employee.getLastName());
        employee.setContractHireDate(Instant.now());
        employees.put(employee.getUuid(), employee);
        return employee;
    }

    private void addMockEmployee(
            String firstName, String lastName, int salary, int age, String jobTitle, String email) {
        SampleEmployee employee = new SampleEmployee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setSalary(salary);
        employee.setAge(age);
        employee.setJobTitle(jobTitle);
        employee.setEmail(email);
        createEmployee(employee);
    }
}
