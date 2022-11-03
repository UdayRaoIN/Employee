package com.india.Employee.Dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO {
	
	@NotBlank(message = "Employee shouldn't be NULL OR EMPTY")
	private String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
	@Past(message = "start date can't be before date from current")
	private Date DOJ;
	@Min(value = 20000, message = "Employee minimum salary should be > 20000")
	private Float Salary;
}
