package com.andreev.utils;

import com.andreev.entity.Employee;
import com.andreev.services.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final EmployeeDetailsService personDetailsService;

    @Autowired
    public PersonValidator(EmployeeDetailsService employeeDetailsService) {
        this.personDetailsService = employeeDetailsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Employee.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Employee employee = (Employee) o;

        try {
            personDetailsService.loadUserByUsername(employee.getName());
        } catch (UsernameNotFoundException ignored) {
            return; // все ок, пользователь не найден
        }

        errors.rejectValue("name", "", "Человек с таким именем пользователя уже существует");
    }
}
