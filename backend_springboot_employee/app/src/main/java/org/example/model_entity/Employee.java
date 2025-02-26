package org.example.model_entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    /*
    Create table employee(
        empid bigInt NOT NULL auto_increment,
        emp_name varchar(50) default null,
        emp_salary float default null,
        emp_age integer default null,
        emp_city varchar(50) default null,
        primary key (empid));
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empid;

    @Column(name = "emp_name")
    private String emp_name;

    @Column(name = "emp_salary")
    private Float emp_salary;

    @Column(name = "emp_age")
    private int empAge;

    @Column(name = "emp_city")
    private String empCity;
}