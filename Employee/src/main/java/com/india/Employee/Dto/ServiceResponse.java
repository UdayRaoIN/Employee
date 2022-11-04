package com.india.Employee.Dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse<T> {

	private HttpStatus status;
	private T response;
	private List<ErrorDTO> errorDto;
	
	
	
	public ServiceResponse(HttpStatus status, List<ErrorDTO> errorDto) {
		super();
		this.status = status;
		this.errorDto = errorDto;
	}



	public ServiceResponse(HttpStatus status, T response) {
		super();
		this.status = status;
		this.response = response;
	}
	
	
	
}
