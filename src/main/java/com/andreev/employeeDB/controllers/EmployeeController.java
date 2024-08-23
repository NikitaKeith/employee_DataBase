package com.andreev.employeeDB.controllers;

import com.andreev.employeeDB.entity.Employee;
import com.andreev.employeeDB.exception_handling.NoSuchEmployeeException;
import com.andreev.employeeDB.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping()//show all
    public String index(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees/index";
    }

    @GetMapping("/{id}")//show one
    public String show(@PathVariable("id") int id, Model model) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        if (employee.isEmpty()) {
            throw new NoSuchEmployeeException("There is no employee with ID =" + id + " in database");
        }
        model.addAttribute("employee", employee);
        return "employees/show";
    }

    @GetMapping("/name/{name}")
    public String show(@PathVariable("name") String name, Model model) {
        Optional<Employee> employee = employeeService.findByName(name);
        if (employee.isEmpty()) {
            throw new NoSuchEmployeeException("There is no employee with name =" + name + " in database");
        }
        model.addAttribute("employee", employee);
        return "employees/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("employee") Employee employee) {
        return "employees/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("employee") @Valid Employee employee,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "employees/new";

        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("employee", employeeService.getEmployee(id));
        return "employees/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult,
                         @PathVariable("id") int id, Model model) {
        if (bindingResult.hasErrors())
            return "employees/edit";
        employeeService.saveEmployee(employee);
        model.addAttribute("employee", employee);
        return "redirect:/employees";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
            employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}