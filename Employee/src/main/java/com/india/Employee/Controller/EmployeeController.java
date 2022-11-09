package com.india.Employee.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.india.Employee.Dto.EmployeeRequestDTO;
import com.india.Employee.Dto.EmployeeResponseDTO;
import com.india.Employee.Dto.ServiceResponse;
import com.india.Employee.Service.EmployeeService;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@PostMapping("/save")
	public ServiceResponse<EmployeeResponseDTO> saveEmployeeData(@RequestBody @Valid EmployeeRequestDTO employeeRequestDTO){
		EmployeeResponseDTO emp= empService.saveEmployee(employeeRequestDTO);
		return new ServiceResponse<EmployeeResponseDTO>(HttpStatus.OK, emp);
	}
	
	
	@GetMapping("/getAllEmployees")
	public ServiceResponse<List<EmployeeResponseDTO>> getAllEmployeeDetails(){
		List<EmployeeResponseDTO> emp=empService.findAllEmployeeDetails();
		return new ServiceResponse<List<EmployeeResponseDTO>>(HttpStatus.OK, emp);
	}
	
	@DeleteMapping("/detele/{id}")
	public ServiceResponse<String> deleteEmployeeById(@PathVariable Integer id){
		String emp=empService.deleteEmployee(id);
		return new ServiceResponse<String>(HttpStatus.OK, emp);
	}
	
	@PutMapping("/updateEmployee/{id}")
	public ServiceResponse<EmployeeResponseDTO> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRequestDTO employeeRequestDTO){
		EmployeeResponseDTO emp= empService.UpdateEmployeeSalary(id, employeeRequestDTO);
		return new ServiceResponse<EmployeeResponseDTO>(HttpStatus.OK, emp);
	}
}
