package com.onkar.employeedetails.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onkar.employeedetails.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
