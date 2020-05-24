package com.onkar.employeedetails.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.onkar.employeedetails.entities.Employee;
import com.onkar.employeedetails.exceptionhandling.ResourceNotFoundException;

@Service
public interface EmployeeService 
{
	public Employee addEmployee(Employee emp) throws Exception;
	
	public void deleteEmployee(long id);
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeById(long id) throws ResourceNotFoundException;

	Employee updateEmployee(Employee emp, long id);
	
}
