package com.india.Employee.Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.india.Employee.Dto.EmployeeRequestDTO;
import com.india.Employee.Dto.EmployeeResponseDTO;
import com.india.Employee.Entity.Employee;

public class AppUtils {
	
	public static Employee mapDTOToEntity(EmployeeRequestDTO employeeRequestDTO) {
		Employee employee=new Employee();
		employee.setName(employeeRequestDTO.getName());
		employee.setSalary(employeeRequestDTO.getSalary());
		employee.setDOJ(employeeRequestDTO.getDOJ());
		return employee;
	}
	
	public static EmployeeResponseDTO mapEnityToDTO(Employee employee) {
		EmployeeResponseDTO employeeResponseDTO=new EmployeeResponseDTO();
		employeeResponseDTO.setEmployeeId(employee.getEmployeeId());
		employeeResponseDTO.setName(employee.getName());
		employeeResponseDTO.setDOJ(employee.getDOJ());
		employeeResponseDTO.setSalary(employee.getSalary());
		return employeeResponseDTO;
	}
	
	public static String convertObjectTOJson(Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
