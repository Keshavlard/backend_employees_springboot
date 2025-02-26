package org.example.controllers;


import org.example.dao_repositories.EmployeeRepository;
import org.example.model_entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/employee")
    public String createNewEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);
        return "Successfully Created Employee";
    }

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> empList = new ArrayList<>(employeeRepository.findAll());
        return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
    }

    @GetMapping("/employee/{empid}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long empid){

        Optional<Employee> emp = employeeRepository.findById(empid);

        //if employee exists
        if(emp.isPresent()){
            return new ResponseEntity<Employee>(emp.get(), HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/employee/{empid}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable long empid, @RequestBody Employee employee){
        Optional<Employee> emp = employeeRepository.findById(empid);

        //if employee exists then update
        if(emp.isPresent()){

            Employee existEmp = emp.get();
            existEmp.setEmpAge(employee.getEmpAge());
            existEmp.setEmpCity(employee.getEmpCity());
            existEmp.setEmp_name(employee.getEmp_name());
            existEmp.setEmp_salary(employee.getEmp_salary());

            employeeRepository.save(employee);
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employee/{empid}")
    public String deleteEmployeeById(@PathVariable long empid){
        employeeRepository.deleteById(empid);
        return "Employee Deleted Successfully";
    }

    @DeleteMapping("/employee")
    public String deleteAllEmployees(){
        employeeRepository.deleteAll();
        return "Deleted all successfully";
    }

    //get employee?emp_city=[keyword] find all emp by city

    @GetMapping("/employee/by-city")
    public ResponseEntity<List<Employee>> getEmpByCity(@RequestParam("emp_city") String empCity) {
        List<Employee> empList = employeeRepository.findByEmpCityIgnoreCase(empCity);
        return ResponseEntity.ok(empList);
    }





    //get employee?empAge=[keyword] find emp whose age>empAge

    @GetMapping("/employee/by-age")
    public List<Employee> getEmployees(@RequestParam("emp_age") int empAge) {
        return employeeRepository.findByEmpAgeGreaterThan(empAge);
    }




}
