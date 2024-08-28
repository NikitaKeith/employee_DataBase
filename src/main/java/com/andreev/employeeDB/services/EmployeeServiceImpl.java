package com.andreev.employeeDB.services;

import com.andreev.employeeDB.entity.Employee;
import com.andreev.employeeDB.repositories.EmployeeRepository;
import com.andreev.employeeDB.repositories.MyUserDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Employee> getAllEmployees() {
    log.info("Getting all employees");
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        log.info("{} was saved", employee.getName());
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployee(int id) {
        log.info("employee {} was got", id);
        return employeeRepository.findById(id);
    }

    @Override
    public void deleteEmployee(int id) {
        log.info("employee {} was deleted", id);
        employeeRepository.deleteById(id);
    }

    @Override
    public Optional<Employee> findByName(String username) {
        log.info("employee {} was found", username);
        return employeeRepository.findByName(username);
    }
}