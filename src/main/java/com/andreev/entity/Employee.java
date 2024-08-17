package com.andreev.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //генерация на стороне БД
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "department")
    private String department;
    @Column(name = "salary")
    private int salary;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;

    public Employee() {
    }

    public Employee(int id, String name, String surname, String department, int salary, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;
        this.password = password;
    }
}