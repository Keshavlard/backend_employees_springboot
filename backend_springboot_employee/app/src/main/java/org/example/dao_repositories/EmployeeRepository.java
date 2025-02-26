package org.example.dao_repositories;

import org.example.model_entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    List<Employee> findByEmpCityIgnoreCase(String empCity);
    List<Employee> findByEmpAgeGreaterThan(int empAge);
}
