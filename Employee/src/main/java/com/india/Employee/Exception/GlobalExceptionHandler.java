package com.india.Employee.Exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.india.Employee.Dto.ErrorDTO;
import com.india.Employee.Dto.ServiceResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ServiceResponse<?> EmployeeBusinessServiceException(MethodArgumentNotValidException exception){
		
		List<ErrorDTO> errorlist=new ArrayList<>();
		exception.getBindingResult().getFieldErrors().forEach(
		error->{ErrorDTO errorDto=new ErrorDTO(error.getField()+" "+error.getDefaultMessage());
		errorlist.add(errorDto);
		});
		
		ServiceResponse<?> serviceResponse= new ServiceResponse<>(HttpStatus.BAD_REQUEST,errorlist);
		return serviceResponse;
		
	}
	
	@ExceptionHandler(EmployeeBusinessServiceException.class)
	public ServiceResponse<?> EmployeeCustomBusinessServiceException(EmployeeBusinessServiceException exception){
		List<ErrorDTO> errorlist=new ArrayList<>();
		errorlist.add(new ErrorDTO(exception.getMessage()));
		ServiceResponse<?> serviceResponse= new ServiceResponse<>(HttpStatus.BAD_REQUEST,errorlist);
		return serviceResponse;
	}
	{
		
	}
}
