package com.onkar.employeedetails.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onkar.employeedetails.entities.Employee;
import com.onkar.employeedetails.exceptionhandling.ResourceNotFoundException;
import com.onkar.employeedetails.repos.EmployeeRepository;
import com.onkar.employeedetails.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService 
{
	@Autowired
	EmployeeRepository empRepository;

	@Override
	public Employee addEmployee(Employee emp) throws Exception{
		return empRepository.save(emp);
	}

	@Override
	public void deleteEmployee(long id) {
		empRepository.deleteById(id);
	}

	@Override
	public Employee updateEmployee(Employee emp, long id)
	{
		return empRepository.findById(id).map(employee -> 
		{
			employee.setFirstName(emp.getFirstName());
			employee.setLastName(emp.getLastName());
			employee.setSalary(emp.getSalary());
			return empRepository.save(employee);
		}).get();
	}

	@Override
	public List<Employee> getAllEmployees() {
		return empRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) throws ResourceNotFoundException {
		return empRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		
	}

}
