package com.andreev.employeeDB.services;

import com.andreev.employeeDB.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Optional<Employee> getEmployee(int id);
    void deleteEmployee(int id);

    Optional<Employee> findByName(String username);
}