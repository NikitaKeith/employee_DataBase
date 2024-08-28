package com.andreev.employeeDB.services;

import com.andreev.employeeDB.entity.Employee;
import com.andreev.employeeDB.repositories.EmployeeRepository;
import com.andreev.employeeDB.repositories.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyEmployeeDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findByName(username);

        return employee.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}