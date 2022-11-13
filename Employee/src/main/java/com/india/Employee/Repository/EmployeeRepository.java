package com.india.Employee.Repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.india.Employee.Entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	List<Employee> findByNameAndSalary(String name,Float Salary);
	
	@Query("from Employee e where e.name=?1")
	List<Employee> findEmployeeByName(String name);
}
