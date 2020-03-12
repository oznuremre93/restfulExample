package com.kafein.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kafein.model.Employee;
import com.kafein.repository.EmployeeRepository;
@Service
@Transactional
public class EmployeeImp implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return (List<Employee>) employeeRepository.findAll();
	}
	@Override
	public Employee insertEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	@Override
	public void deleteEmployee(Integer id) {
		employeeRepository.deleteById(id);
	}
	@Override
	public Employee updateEmployee(Employee employee) {
		
		if(checkId(employee.getId())) {
			
			System.out.println("bu id var ");
			return employeeRepository.save(employee);
		}
		else {
			
			System.out.println("bu id yok ");
			return null;
		}
	}
	@Override
	public Boolean checkId(Integer id) {
		if(id==null) {
			return false;
		}
		Optional<Employee> member = employeeRepository.findById(id);
		return member.isPresent();
	}
	@Override
	public Boolean checkMail(String email) {
		Employee mailCheck = employeeRepository.getByEmail(email);
		return mailCheck !=null ? true:false;
	}
		
}
