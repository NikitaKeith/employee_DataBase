package com.andreev.employeeDB.services;

import com.andreev.employeeDB.entity.Employee;
import com.andreev.employeeDB.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployee(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Optional<Employee> findByName(String username) {
        return employeeRepository.findByName(username);
    }
}