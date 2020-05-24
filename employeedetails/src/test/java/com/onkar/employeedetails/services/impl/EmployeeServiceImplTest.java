package com.onkar.employeedetails.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.onkar.employeedetails.entities.Employee;
import com.onkar.employeedetails.exceptionhandling.ResourceNotFoundException;
import com.onkar.employeedetails.repos.EmployeeRepository;

class EmployeeServiceImplTest {
	
	@Mock
	EmployeeRepository empRepository;
	
	@InjectMocks
	EmployeeServiceImpl employeeService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testAddEmployee() throws Exception
	{
		Employee mockEmp = new Employee();
		mockEmp.setId(1L);
		mockEmp.setFirstName("ABC");
		mockEmp.setLastName("ABC");
		mockEmp.setSalary(500);
		
		Mockito.when(empRepository.save(Mockito.any(Employee.class))).thenReturn(mockEmp);
		
		assertEquals(employeeService.addEmployee(mockEmp), mockEmp);
	}

	@Test
	void testDeleteEmployee() 
	{
		Employee mockEmp = new Employee();
		mockEmp.setId(1L);
		mockEmp.setFirstName("ABC");
		mockEmp.setLastName("ABC");
		mockEmp.setSalary(500);
		
		Optional<Employee> empOptional = Optional.of(mockEmp);
		
		Mockito.when(empRepository.findById(Mockito.anyLong())).thenReturn(empOptional);
	    Mockito.when(empRepository.existsById(mockEmp.getId())).thenReturn(false);
	    
	    assertFalse(empRepository.existsById(mockEmp.getId()));
	}

	@Test
	void testUpdateEmployee() throws Exception 
	{
		Employee mockEmp = new Employee();
		mockEmp.setId(1L);
		mockEmp.setFirstName("ABC");
		mockEmp.setLastName("ABC");
		mockEmp.setSalary(500);
		
		Optional<Employee> empOptional = Optional.of(mockEmp);
		
		Mockito.when(empRepository.findById(Mockito.anyLong())).thenReturn(empOptional);
		mockEmp.setSalary(8000);
		Mockito.when(empRepository.save(mockEmp)).thenReturn(mockEmp);
		
		assertThat(employeeService.updateEmployee(mockEmp, 1)).isEqualTo(mockEmp);
	}

	@Test
	void testGetAllEmployees() 
	{
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Employee(1, "ABC", "ABC", 500));
		employeeList.add(new Employee(1, "ABC", "ABC", 500));
		
		Mockito.when(empRepository.findAll()).thenReturn(employeeList);
		
		assertNotNull(employeeService.getAllEmployees());
		assertEquals(employeeService.getAllEmployees(), employeeList);
	}

	@Test
	void testGetEmployeeById() throws ResourceNotFoundException 
	{
		Employee mockEmp = new Employee();
		mockEmp.setId(1L);
		mockEmp.setFirstName("ABC");
		mockEmp.setLastName("ABC");
		mockEmp.setSalary(500);
		
		Optional<Employee> empOptional = Optional.of(mockEmp);
		
		Mockito.when(empRepository.findById(Mockito.anyLong())).thenReturn(empOptional);
		
		Employee emp = employeeService.getEmployeeById(8);
		
		assertNotNull(emp);
		assertEquals(mockEmp, emp);
	}
	
	@Test
	void testGetEmployeeById_ResourceNotFoundException()
	{
		Mockito.when(empRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> employeeService.getEmployeeById(8));
	}

}
