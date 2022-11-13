package com.india.Employee.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.india.Employee.Entity.Employee;
import com.india.Employee.Service.EmployeeService;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@PostMapping("/save")
	public ServiceResponse<EmployeeResponseDTO> saveEmployeeData(@RequestBody @Valid EmployeeRequestDTO employeeRequestDTO){
		EmployeeResponseDTO emp= empService.saveEmployee(employeeRequestDTO);
		System.out.println(new ServiceResponse<>(HttpStatus.OK, emp));
		return new ServiceResponse<>(HttpStatus.OK, emp);
	}
	
	
	@GetMapping("/getAllEmployees")
	public ServiceResponse<List<EmployeeResponseDTO>> getAllEmployeeDetails(){
		List<EmployeeResponseDTO> emp=empService.findAllEmployeeDetails();
		return new ServiceResponse<>(HttpStatus.OK, emp);
	}
	
	@GetMapping("/findByNameAndSal/{name}/By/{sal}")
	public ServiceResponse<List<EmployeeResponseDTO>> getEmployeeDetailsByNameAndSal(@PathVariable String name,@PathVariable float sal){
		List<EmployeeResponseDTO> emp=empService.findByNameAndSal(name, sal);
		return new ServiceResponse<>(HttpStatus.OK, emp);
	}
	
	@DeleteMapping("/detele/{id}")
	public ServiceResponse<String> deleteEmployeeById(@PathVariable Integer id){
		String emp=empService.deleteEmployee(id);
		return new ServiceResponse<>(HttpStatus.OK, emp);
	}
	
	@PutMapping("/updateEmployee/{id}")
	public ServiceResponse<EmployeeResponseDTO> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRequestDTO employeeRequestDTO){
		EmployeeResponseDTO emp= empService.UpdateEmployeeSalary(id, employeeRequestDTO);
		return new ServiceResponse<>(HttpStatus.OK, emp);
	}
	
	@GetMapping("/getPage/{offset}/{limit}")
	public Page<Employee> getEmployeesInPage(@PathVariable int offset,@PathVariable int limit){
		return empService.getAllDetailsPage(offset, limit);
	}
}
