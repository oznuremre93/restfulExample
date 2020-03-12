package com.kafein.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kafein.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	public Employee getByEmail(String email);
	
}
