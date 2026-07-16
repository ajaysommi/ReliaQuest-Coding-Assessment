package com.challenge.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.challenge.api.model.Employee;
import com.challenge.api.model.SampleEmployee;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService();

    @Test
    void returnsAllMockEmployees() {
        assertEquals(3, employeeService.getAllEmployees().size());
    }

    @Test
    void createEmployeeFillsInServerFields() {
        SampleEmployee request = new SampleEmployee();
        request.setFirstName("Test");
        request.setLastName("Person");
        Employee created = employeeService.createEmployee(request);
        assertNotNull(created.getUuid());
        assertEquals("Test Person", created.getFullName());
    }

    @Test
    void createEmployeeFailsWithoutName() {
        SampleEmployee request = new SampleEmployee();
        assertThrows(ResponseStatusException.class, () -> employeeService.createEmployee(request));
    }
}
