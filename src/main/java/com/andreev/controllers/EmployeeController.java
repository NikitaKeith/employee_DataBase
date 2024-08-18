package com.andreev.controllers;

import com.andreev.entity.Employee;
import com.andreev.exception_handling.NoSuchEmployeeException;
import com.andreev.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
//@EnableWebMvc
@PropertySource("classpath:hibernate.properties")
public class EmployeeController {

    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {this.employeeService = employeeService;}

    @RequestMapping()
    public String index(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with ID =" + id + " in database");
        }
        model.addAttribute("employee", employee);
        return "employees/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("employee") Employee employee) {
        //??
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

        model.addAttribute("employee", employee);

        return "redirect:/employees";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
            employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

}