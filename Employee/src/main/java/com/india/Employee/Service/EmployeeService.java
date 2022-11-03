package com.india.Employee.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.india.Employee.Dto.EmployeeRequestDTO;
import com.india.Employee.Dto.EmployeeResponseDTO;
import com.india.Employee.Entity.Employee;
import com.india.Employee.Repository.EmployeeRepository;
import com.india.Employee.Util.AppUtils;

@Service
public class EmployeeService {

	
	@Autowired
	private EmployeeRepository empRepo;
	
	
	public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO employeeRequestDTO ){
	Employee emp=AppUtils.mapDTOToEntity(employeeRequestDTO);
	Employee empData=empRepo.save(emp);
	EmployeeResponseDTO empResponse=AppUtils.mapEnityToDTO(empData);
	return empResponse;
	
	}
	
}
