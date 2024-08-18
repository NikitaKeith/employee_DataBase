package com.andreev.services;

import com.andreev.entity.Employee;
import com.andreev.security.EmployeeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeDetailsService implements UserDetailsService {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeDetailsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Employee> person = employeeService.findByUsername(s);

        if (!person.isPresent())
            throw new UsernameNotFoundException("User not found");

        return new EmployeeDetails(person.get());
    }
}
