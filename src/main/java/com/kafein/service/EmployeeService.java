package com.kafein.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.kafein.model.Employee;
import com.kafein.model.EmployeeDTO;


public interface EmployeeService {

	 public List<Employee> getAllEmployee();
	 public Employee insertEmployee(Employee employee);
	 public void deleteEmployee(Integer id);
	 public Boolean checkMail(String email);
	 public Boolean checkId (Integer id);
	 public Employee updateEmployee(Employee employee);
	 
}
