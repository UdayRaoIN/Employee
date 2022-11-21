package com.india.Employee.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.india.Employee.Dto.EmployeeRequestDTO;
import com.india.Employee.Dto.EmployeeResponseDTO;
import com.india.Employee.Entity.Employee;
import com.india.Employee.Exception.EmployeeBusinessServiceException;
import com.india.Employee.Repository.EmployeeRepository;
import com.india.Employee.Util.AppUtils;

@Service
public class EmployeeService {


	@Autowired
	private EmployeeRepository empRepo;

	Logger log=LoggerFactory.getLogger(EmployeeService.class);

	public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO employeeRequestDTO ){
		log.info("EmployeeService::saveEmployee method execution started");
		Employee emp=AppUtils.mapDTOToEntity(employeeRequestDTO);
		EmployeeResponseDTO empResponse=null;
		try {
			if(emp.getSalary()<10000000) {
				Employee empData=empRepo.save(emp);
				log.debug("EmployeeService::saveEmployeem method response from database {}",AppUtils.convertObjectTOJson(empData));
				empResponse=AppUtils.mapEnityToDTO(empData);
				log.debug("EmployeeService::saveEmployeem method response {}",AppUtils.convertObjectTOJson(empResponse));
			}
		}
		catch (Exception e) {
			log.info("EmployeeService::saveEmployee exception occured while persisting data into database");
			throw new EmployeeBusinessServiceException("onboarding failed!! Please contact admin@info.com");
		}	
		log.info("EmployeeService::saveEmployee method execution ended");
		return empResponse;
	}

	public List<EmployeeResponseDTO> findAllEmployeeDetails() {
		log.info("EmployeeService::findAllEmployeeDetails method execution started");
		Iterable<Employee> iEmp=null;
		try {
			
			iEmp=empRepo.findAll();
			log.debug("EmployeeService::findAllEmployeeDetails method response from database {}",AppUtils.convertObjectTOJson(iEmp));
			log.info("EmployeeService::findAllEmployeeDetails method execution ended");
			return StreamSupport.stream(iEmp.spliterator(), false).map(x->AppUtils.mapEnityToDTO(x)).collect(Collectors.toList());
		}
		catch (Exception e) {
			log.info("EmployeeService::findAllEmployeeDetails exception occured while fetching data into database");
			throw new EmployeeBusinessServiceException("retrieval failed !! Please contact admin@info.com");
		}
	}
	
	public String deleteEmployee(Integer id) {
		Employee emp=null;
		try {
			emp=empRepo.findById(id).get();
			empRepo.deleteById(emp.getEmployeeId());
		}
		catch (Exception e) {
			throw new EmployeeBusinessServiceException("delete failed !! Please contact admin@info.com");
		}
		return "employee with "+id+" deleted"; 
		
	}
	
	
	public EmployeeResponseDTO UpdateEmployeeSalary(Integer id,EmployeeRequestDTO employeeRequestDTO) {
		Employee emp=null;
		try {
			emp=empRepo.findById(id).orElse(null);
			emp.setSalary(employeeRequestDTO.getSalary());
			empRepo.save(emp);
		}
		catch (Exception e) {
			throw new EmployeeBusinessServiceException("delete failed !! Please contact admin@info.com");
		}
		return AppUtils.mapEnityToDTO(emp);
		
	}
	
	public List<EmployeeResponseDTO> findByNameAndSal(String name,float salary) {
		List<Employee> emp=null;
		try {
			emp=empRepo.findByNameAndSalary(name, salary);
			if(!emp.isEmpty()) {
			return StreamSupport.stream(emp.spliterator(), false).map(x->AppUtils.mapEnityToDTO(x)).collect(Collectors.toList());
			}
			else {
				throw new EmployeeBusinessServiceException("retrieval failed !! Please contact admin@info.com");
			}
		}
		catch (Exception e) {
			throw new EmployeeBusinessServiceException("retrieval failed !! Please contact admin@info.com");
		}
	}
	
	public List<EmployeeResponseDTO> findEmployeeByName(String name){
		List<Employee> emp=null;
		try {
			emp=empRepo.findEmployeeByName(name);
			return StreamSupport.stream(emp.spliterator(), false).map(AppUtils::mapEnityToDTO).collect(Collectors.toList());
		}
		catch (Exception e) {
			throw new EmployeeBusinessServiceException("retrieval failed !! Please contact admin@info.com");
		}
	}
	
	public Page<Employee> getAllDetailsPage(int offset,int limit){
		return empRepo.findAll(PageRequest.of(offset, limit));
	}

}
