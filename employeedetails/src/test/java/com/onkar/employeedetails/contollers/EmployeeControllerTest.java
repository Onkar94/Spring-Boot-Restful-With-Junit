package com.onkar.employeedetails.contollers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onkar.employeedetails.entities.Employee;
import com.onkar.employeedetails.services.EmployeeService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EmployeeControllerTest {
	
	@Mock
	EmployeeService empService;
	
	@InjectMocks
	EmployeeController employeeController;
	
	Employee mockEmp;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		mockEmp = new Employee();
		mockEmp.setId(5);
		mockEmp.setFirstName("Rohan");
		mockEmp.setLastName("Thomas");
		mockEmp.setSalary(35000);
	}

	@Test
	void testGetAllEmployees() throws Exception 
	{	
		List<Employee> mockEmpList = new ArrayList<Employee>();
		mockEmpList.add(mockEmp);
		mockEmpList.add(new Employee(7, "Anvay", "Kulkarni", 50000));
		
		String URI = "/employees";
		Mockito.when(empService.getAllEmployees()).thenReturn(mockEmpList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()", is(9)));
	}

	@Test
	void testGetEmployeeById() throws Exception 
	{	
		Mockito.when(empService.getEmployeeById(Mockito.anyLong())).thenReturn(mockEmp);
		
		String URI = "/employees/5";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(mockEmp);
		String outputInJson = result.getResponse().getContentAsString();
		assertEquals(expectedJson, outputInJson);
	}

	@Test
	void testAddEmployee() throws Exception 
	{
		String inputJson = this.mapToJson(mockEmp);
		String URI = "/employees";
		
		Mockito.when(empService.addEmployee(Mockito.any(Employee.class))).thenReturn(mockEmp);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)
				.content(inputJson)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String outputInJson = result.getResponse().getContentAsString();
		assertEquals(inputJson, outputInJson);
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	void testUpdateEmployee() throws Exception 
	{
		String URI = "/employees/5";
		
		Mockito.when(empService.addEmployee(Mockito.any(Employee.class))).thenReturn(mockEmp);
		mockEmp.setSalary(8000);
		String inputJson = this.mapToJson(mockEmp);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI)
				.content(inputJson)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String outputInJson = result.getResponse().getContentAsString();
		assertEquals(inputJson, outputInJson);
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	void testDeleteEmployee() throws Exception 
	{
		String URI = "/employees/3";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(requestBuilder)
				 .andExpect(status().isOk());
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
