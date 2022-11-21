package com.india.Employee.Controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.india.Employee.Util.AppUtils;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
	
	Logger log=LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService empService;
	
	@PostMapping("/save")
	public ServiceResponse<EmployeeResponseDTO> saveEmployeeData(@RequestBody @Valid EmployeeRequestDTO employeeRequestDTO){
		log.info("EmployeeController::saveEmployeeData Request payload:{}",AppUtils.convertObjectTOJson(employeeRequestDTO));
		EmployeeResponseDTO emp= empService.saveEmployee(employeeRequestDTO);
		log.info("EmployeeController::saveEmployeeData Response payload:{}",AppUtils.convertObjectTOJson(emp));
		return new ServiceResponse<>(HttpStatus.OK, emp);
	}
	
	
	@GetMapping("/getAllEmployees")
	public ServiceResponse<List<EmployeeResponseDTO>> getAllEmployeeDetails(){
		log.info("EmployeeController::getAllEmployeeDetails Request");
		List<EmployeeResponseDTO> emp=empService.findAllEmployeeDetails();
		log.info("EmployeeController::getAllEmployeeDetails Response payload:{}",AppUtils.convertObjectTOJson(emp));
		return new ServiceResponse<>(HttpStatus.OK, emp);
	}
	
	@GetMapping("/findByNameAndSal/{name}/By/{sal}")
	public ServiceResponse<List<EmployeeResponseDTO>> getEmployeeDetailsByNameAndSal(@PathVariable String name,@PathVariable float sal){
		log.info("EmployeeController::getEmployeeDetailsByNameAndSal Request payload:{},{}",name,sal);
		List<EmployeeResponseDTO> emp=empService.findByNameAndSal(name, sal);
		log.info("EmployeeController::getEmployeeDetailsByNameAndSal Response payload:{}",AppUtils.convertObjectTOJson(emp));
		return new ServiceResponse<>(HttpStatus.OK, emp);
	}
	
	@DeleteMapping("/detele/{id}")
	public ServiceResponse<String> deleteEmployeeById(@PathVariable Integer id){
		log.info("EmployeeController::deleteEmployeeById Request {}",id);
		String emp=empService.deleteEmployee(id);
		log.info("EmployeeController::deleteEmployeeById Response {}",AppUtils.convertObjectTOJson(emp));
		return new ServiceResponse<>(HttpStatus.OK, emp);
	}
	
	@PutMapping("/updateEmployee/{id}")
	public ServiceResponse<EmployeeResponseDTO> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRequestDTO employeeRequestDTO){
		log.info("EmployeeController::updateEmployee Request {},{}",id,AppUtils.convertObjectTOJson(employeeRequestDTO));
		EmployeeResponseDTO emp= empService.UpdateEmployeeSalary(id, employeeRequestDTO);
		log.info("EmployeeController::updateEmployee Response {}",AppUtils.convertObjectTOJson(emp));
		return new ServiceResponse<>(HttpStatus.OK, emp);
	}
	
	@GetMapping("/getPage/{offset}/{limit}")
	public Page<Employee> getEmployeesInPage(@PathVariable int offset,@PathVariable int limit){
		return empService.getAllDetailsPage(offset, limit);
	}
}
