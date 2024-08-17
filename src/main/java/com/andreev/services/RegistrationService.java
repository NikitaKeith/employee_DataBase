package com.andreev.services;

import com.andreev.entity.Employee;
import com.andreev.repositories.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(EmployeeService employeeService, PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRole("ROLE_USER");
        employeeService.saveEmployee(employee);
    }
}
