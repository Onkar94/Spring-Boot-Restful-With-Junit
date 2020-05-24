package com.onkar.employeedetails.contollers;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.onkar.employeedetails.entities.Employee;
import com.onkar.employeedetails.exceptionhandling.ResourceNotFoundException;
import com.onkar.employeedetails.services.EmployeeService;

@RestController()
public class EmployeeController 
{
	@Autowired
	EmployeeService empService;
	
	@GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getAllEmployees()
	{
		return empService.getAllEmployees();
	}
	
	@GetMapping(value = "employees/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee getEmployeeById(@PathVariable("empId") long id) throws ResourceNotFoundException
	{
		return empService.getEmployeeById(id);
	}
	
	@RequestMapping(value = "/employees", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Employee addEmployee(@RequestBody Employee emp) throws Exception
	{
		return empService.addEmployee(emp);
	}
	
	@RequestMapping(value = "/employees/{empId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Employee updateEmployee(@RequestBody Employee emp, @PathVariable("empId") long id)
	{
		return empService.updateEmployee(emp, id);
	}
	
	@RequestMapping(value = "employees/{empId}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable("empId") long id)
	{
		empService.deleteEmployee(id);
	}

}
